package vista;

import javax.swing.*;
import control.*;
import java.awt.*;
import java.awt.event.*;
import modelo.*;
public class Formulario extends JFrame{
    
    JPanel panelSup = new JPanel();
    JPanel panelMed = new JPanel();
    JPanel panelInf = new JPanel();
    
    public Formulario(){
        
        //Invoco al constructor de la superclase indicandole el titulo de la ventana
        
        super("Agenda De Contactos");
        
        //invoco el metodo initcomponets que inicializa los diferentes elemetos de la GUI
        
        initComponents();
        //Color fondo
        Color colorboton =new Color (255, 204, 204);
        panelSup.setBackground(colorboton);
        panelMed.setBackground(colorboton);
        panelInf.setBackground(colorboton);
        
        //icono
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/icono.png"));
        setIconImage(icon);
        setVisible(true);
    }
    
    public void initComponents(){
        
        //Determino el Layout principal del contenedor:
        
        setLayout(new GridLayout(3,1));
        
        //Agrego a cada grilla cada uno de los paneles
        
        add(panelSup);
        add(panelMed);
        add(panelInf);
        
        //Asigno los diferentes Layout que se usaran en cada panel:
        
        panelSup.setLayout(new FlowLayout());
        panelMed.setLayout(new GridLayout(4,2));
        panelInf.setLayout(new FlowLayout());
        
        //A Continuacion cramos los diferentes componentes de la GUI:
        
        //Para el panel superior una etiqueta para el titulo:
        
        JLabel titulo;
        
        //Para el panel medio 4 etiquetas y 4 campos de texto:
        
        JLabel labNombre, labTelef, labEmail, labEdad;
        JTextField jtfNombre, jtfTelef, jtfEmail, jtfEdad;
        
        //Para el panel inferior, 4 botones:
        
        JButton botGuardar, botConsultar, botLimpiar, botSalir;
        
        //Ahora instanciamos cada componente y lo agregamos al panel respectivo:
        
        titulo = new JLabel();
        titulo.setText("AGENDA DE CONTACTOS");
        panelSup.add(titulo);
        
        labNombre = new JLabel();
        labNombre.setText("Nombre: ");
        panelMed.add(labNombre);
        
        jtfNombre = new JTextField();
        panelMed.add(jtfNombre);
        
        labTelef = new JLabel();
        labTelef.setText("Telefono: ");
        panelMed.add(labTelef);
        
        jtfTelef = new JTextField();
        panelMed.add(jtfTelef);
        
        labEmail = new JLabel();
        labEmail.setText("Email: ");
        panelMed.add(labEmail);
        
        jtfEmail = new JTextField();
        panelMed.add(jtfEmail);
        
        labEdad = new JLabel();
        labEdad.setText("Edad: ");
        panelMed.add(labEdad);
        
        jtfEdad = new JTextField();
        panelMed.add(jtfEdad);
        //botones
        Color colorboton =new Color (255, 255, 255);
        Font fuenteguardar= new Font("Tahoma", Font.PLAIN, 11);
        botGuardar = new JButton("Guardar");
        botGuardar.setFont(fuenteguardar);
        panelInf.add(botGuardar);
        botGuardar.setBackground(colorboton);
        
        botConsultar = new JButton("Consultar");
        panelInf.add(botConsultar);
        botConsultar.setBackground(colorboton);
        
        botLimpiar = new JButton("Limpiar");
        panelInf.add(botLimpiar);
        botLimpiar.setBackground(colorboton);
        
        botSalir = new JButton("Salir");
        panelInf.add(botSalir);
        botSalir.setBackground(colorboton);
        //Hago Visible el contenedor y sus componentes:
        
        setVisible(true);
        
        //Predetermino que la aplicacion se cierre al cerrarse la ventana:
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        botLimpiar.addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
            jtfNombre.setText("");
            jtfEmail.setText("");
            jtfEdad.setText("");
            jtfTelef.setText("");
        }
    });
    
    botGuardar.addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
            try{
            if(ControlAgenda.registrarContacto(jtfNombre.getText(), jtfTelef.getText(), jtfEmail.getText(), Integer.parseInt(jtfEdad.getText())))
                JOptionPane.showMessageDialog(null, "Contacto guardado");
            else JOptionPane.showMessageDialog(null, "Contacto no guardado");
        }
        catch(NumberFormatException nfe){
            JOptionPane.showMessageDialog(null, "No se pudo registrar el contacto");
        }}});
    //evento boton salir}
    botSalir.addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
            System.exit(0);//cierra la ventana
        }
    });
    
    
    //evento boton buscar
    botConsultar.addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
            Contacto c= ControlAgenda.buscarContacto(jtfNombre.getText());
            
            if(c==null)
                JOptionPane.showMessageDialog(null, "Contacto no encontrado");
            else{
                jtfTelef.setText(c.getTelefono());
                jtfEmail.setText(c.getEmail());
                jtfEdad.setText(String.valueOf(c.getEdad()));
            }
        }});
    
    }
    
    }    
    
    

