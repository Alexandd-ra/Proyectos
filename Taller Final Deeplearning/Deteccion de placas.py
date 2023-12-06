import cv2
import numpy as np
import pytesseract
from PIL import Image

cap = cv2.VideoCapture(0)
Ctexto = ''

while True:
    ret, frame = cap.read()

    if ret is False:
        break

    al, an, c = frame.shape

    x1 = int(an / 3)
    x2 = int(x1 * 2)

    y1 = int(al / 3)
    y2 = int(y1 * 2)

    cv2.rectangle(frame, (x1, y1), (x2, y2), (0, 255, 0), 2)

    recorte = frame[y1:y2, x1:x2]

    mB = recorte[:, :, 0]
    mG = recorte[:, :, 1]
    mR = recorte[:, :, 2]

    Color = cv2.absdiff(mG, mB)

    _, umbral = cv2.threshold(Color, 40, 255, cv2.THRESH_BINARY)

    contornos, _ = cv2.findContours(umbral, cv2.RETR_TREE, cv2.CHAIN_APPROX_SIMPLE)

    contornos = sorted(contornos, key=lambda x: cv2.contourArea(x), reverse=True)

    for contorno in contornos:
        area = cv2.contourArea(contorno)
        if 500 < area < 5000:
            x, y, ancho, alto = cv2.boundingRect(contorno)
            xpi, ypi, xpf, ypf = x + x1, y + y1, x + ancho + x1, y + alto + y1

            cv2.rectangle(frame, (xpi, ypi), (xpf, ypf), (255, 255, 0), 2)

            placa = frame[ypi:ypf, xpi:xpf]
            alp, anp, _ = placa.shape

            Mva = np.zeros((alp, anp))

            mBp = placa[:, :, 0]
            mGp = placa[:, :, 1]
            mRp = placa[:, :, 2]

            for col in range(alp):
                for fil in range(anp):
                    Max = max(mRp[col, fil], mGp[col, fil], mBp[col, fil])
                    Mva[col, fil] = 255 - Max

            _, bin = cv2.threshold(Mva, 150, 255, cv2.THRESH_BINARY)

            bin = bin.reshape(alp, anp)
            bin = Image.fromarray(bin)
            bin = bin.convert("L")

            if 36 < alp < 82:
                pytesseract.pytesseract.tesseract_cmd = r'C:\Program Files\Tesseract-OCR\tesseract.exe'
                config = "--psm 1"
                texto = pytesseract.image_to_string(bin, config=config)

                if len(texto) >= 7:
                    Ctexto = texto

            break

    # Texto "Procesando placa"
    cv2.putText(frame, 'Procesando placa', (x1 - 30, y1 + 200), cv2.FONT_HERSHEY_SIMPLEX, 1, (0, 255, 0), 2)

    # Texto Ctexto
    cv2.rectangle(frame, (x1, y1 + 220), (x2, y1 + 260), (0, 0, 0), cv2.FILLED)
    cv2.putText(frame, Ctexto[0:7], (x1 + 40, y1 + 250), cv2.FONT_HERSHEY_SIMPLEX, 1, (0, 255, 0), 2)
    
    cv2.imshow("vehiculos", frame)

    t = cv2.waitKey(1)
    if t == ord('q') or t == 27:
        break

cap.release()
cv2.destroyAllWindows()

