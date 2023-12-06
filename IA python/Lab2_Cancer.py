#!/usr/bin/env python
# coding: utf-8

# División de imágenes (Train y Val)

# In[1]:


#Para crear y dividir las carpetas
import shutil
import os
from shutil import copyfile
import random
import glob


# In[2]:


# Directorio de la base de datos
data_dir = 'C:/Users/Sebastian/Documents/UNIVERSIDAD/9 SEMESTRE/Deep Learning/2 Corte/Laboratorios/bds/breast_histopathology_images'


# In[3]:


# Ruta completa para la carpeta raíz 'train_test' fuera de 'data_dir'
train_test_root = 'C:/Users/Sebastian/Documents/UNIVERSIDAD/9 SEMESTRE/Deep Learning/2 Corte/Laboratorios/bds/CancerSeno'


# In[4]:


# Crear directorios de entrenamiento y prueba dentro de la carpeta raíz
train_dir = os.path.join(train_test_root, 'train')
test_dir = os.path.join(train_test_root, 'val')
os.makedirs(train_dir, exist_ok=True)
os.makedirs(test_dir, exist_ok=True)


# In[5]:


# Crear subdirectorios para cáncer y sano en los conjuntos de entrenamiento y prueba
os.makedirs(os.path.join(train_dir, 'cancer'), exist_ok=True)
os.makedirs(os.path.join(train_dir, 'sano'), exist_ok=True)
os.makedirs(os.path.join(test_dir, 'cancer'), exist_ok=True)
os.makedirs(os.path.join(test_dir, 'sano'), exist_ok=True)


# In[6]:


# Obtener la lista de carpetas de lotes (por ejemplo, 8863, 8864, 8865)
batch_folders = glob.glob(os.path.join(data_dir, '*')) 


# In[7]:


# Proporción para dividir las imágenes en entrenamiento (70%) y prueba (30%)
train_ratio = 0.7


# In[8]:


# Iterar a través de las carpetas de lotes
for batch_folder in batch_folders:
    # Obtener la lista de imágenes de células cancerígenas (etiqueta 1)
    cancer_images = glob.glob(os.path.join(batch_folder, '1', '*.png'))
    
    # Obtener la lista de imágenes de células sanas (etiqueta 0)
    sano_images = glob.glob(os.path.join(batch_folder, '0', '*.png'))
    
    # Mezclar aleatoriamente las imágenes de cada clase
    random.shuffle(cancer_images)
    random.shuffle(sano_images)
    
    # Calcular la cantidad de imágenes para entrenamiento y prueba
    num_train_cancer = int(len(cancer_images) * train_ratio)
    num_test_cancer = len(cancer_images) - num_train_cancer
    num_train_sano = int(len(sano_images) * train_ratio)
    num_test_sano = len(sano_images) - num_train_sano
    
    # Dividir y copiar las imágenes en los conjuntos de entrenamiento y prueba
    for i, image in enumerate(cancer_images):
        if i < num_train_cancer:
            dest_dir = os.path.join(train_dir, 'cancer')
        else:
            dest_dir = os.path.join(test_dir, 'cancer')
        shutil.copy(image, os.path.join(dest_dir, os.path.basename(image)))
    
    for i, image in enumerate(sano_images):
        if i < num_train_sano:
            dest_dir = os.path.join(train_dir, 'sano')
        else:
            dest_dir = os.path.join(test_dir, 'sano')
        shutil.copy(image, os.path.join(dest_dir, os.path.basename(image)))


# # ***Creación de la Red Neuronal***
# 
# Caso 1: Red neuronal convolucional

# In[25]:


#Para la red convolucional
import tensorflow as tf
import numpy as np
from tensorflow.keras.models import Sequential
from sklearn.metrics import classification_report
from tensorflow.keras.layers import Conv2D, MaxPooling2D, Flatten, Dense
from tensorflow.keras.preprocessing.image import ImageDataGenerator


# In[26]:


# Rutas a los datos
train_dir = train_test_root + '/train'
val_dir = train_test_root + '/val'


# In[27]:


# Crear generators con aumento de datos
train_data = tf.keras.preprocessing.image.ImageDataGenerator(
    rescale=1./255,
    rotation_range=20,
    width_shift_range=0.2,
    height_shift_range=0.2,
    shear_range=0.2,
    zoom_range=0.2,
    horizontal_flip=True,
    fill_mode='nearest'
)

val_data = tf.keras.preprocessing.image.ImageDataGenerator(rescale=1./255)

img_size = 256

train_generator = train_data.flow_from_directory(
    train_dir,
    target_size=(img_size, img_size),
    class_mode='categorical',
    batch_size=32
)

val_generator = val_data.flow_from_directory(
    val_dir,
    target_size=(img_size, img_size),
    class_mode='categorical',
    batch_size=32
)


# In[28]:


# Crear modelo CNN
model = Sequential()
model.add(Conv2D(32, (3, 3), activation='relu', input_shape=(img_size, img_size, 3)))
model.add(MaxPooling2D(pool_size=(2, 2)))

model.add(Conv2D(64, (3, 3), activation='relu'))
model.add(MaxPooling2D(pool_size=(2, 2)))

model.add(Conv2D(128, (3, 3), activation='relu'))
model.add(MaxPooling2D(pool_size=(2, 2)))

model.add(Flatten())
model.add(Dense(512, activation='relu'))
model.add(Dense(2, activation='softmax'))


# In[29]:


# Compilar y entrenar
model.compile(loss='categorical_crossentropy', optimizer='adam', metrics=['accuracy'])

model.summary()


# In[30]:


model.fit(train_generator, validation_data=val_generator, epochs=3, batch_size=64)


# In[ ]:


_, accuracy = model.evaluate(val_generator)
print('Accuracy: %.2f' % (accuracy*100))


# In[ ]:


print("[INFO] Evaluating network...")
val_generator.reset()
predIdxs = model.predict(val_generator, verbose=1)

predIdxs = np.argmax(predIdxs, axis=1)
print(classification_report(val_generator.classes, predIdxs))

