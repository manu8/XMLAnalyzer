package ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JTabbedPane;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import logic.XMLAddons;
import logic.XMLValidator;

/**
 * Provee de la interfaz de usuario
 * @author Andrés Moreno Aguilar
 */
public class GUI extends JFrame{
	private final String XML = "XML";
	private final String XSLT = "XSLT";
	private final String FileGenerated = "GENERATED";
	
	//ATRIBUTOS*****************
	private JPanel contentPane;
	private JPanel panelJTree;
	
	private JMenuBar menuBar;
	private JFileChooser archivoX;
	private JPanel ventana;
	
	private JLabel logo;
	private JTree tree;
	private JPanel panel;
	
	private JButton bt_XML;
	private JButton bt_DTD;
	private JButton bt_XSD;
	
	private JTabbedPane PanelPestanias;	
	private JPanel pestania_1;
	private JPanel pestania_2;
	private JPanel pestania_3;
	
	private JTextArea textArea_XML;
	private JTextArea textArea_XML_BienFormado;
	private JScrollPane scrollPane_XML;
	private JScrollPane scrollPane_XSLT;
	
	private JTextArea textArea_XSLT;
	
	private JButton bt_Guardar;
	
	private JButton bt_Editar;
	private JButton bt_Ejecutar;
	private JButton bt_Mostrar;
	
	private JTextArea textArea_Expresiones;
	private JScrollPane scrollPane_Expresiones;
	private JButton bt_EjecutarXpath;
	private JButton bt_EjecutarXquery;
	private JButton bt_CargarXquery;
	
	private JLabel pie;
	

	private File xmlFile = new File("assets\\samples\\contacts.xml");
	private File xslFile = new File("assets\\samples\\Stylesheet.xsl");
	private File generatedFile;
	private final  XMLValidator validator = new XMLValidator();
	private Document doc;


	
	//CONSTRUCTOR*************
	public GUI() {
		//-------------------------
		
		//PANEL CONTENEDOR
		contentPane = new JPanel();
		contentPane.setBounds(0, 0, 934, 661);
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//LOGOTIPO
		logo = new JLabel("");
		logo.setIcon(new ImageIcon("assets\\img\\logoXML.png"));
		logo.setBackground(Color.WHITE);
		logo.setBounds(35, 23, 184, 89);
		contentPane.add(logo);

		
//***************** JTREE *******************
		//PANEL JTREE
		panelJTree = new JPanel();
		panelJTree.setBounds(36, 132, 183, 494);
		panelJTree.setBackground(new Color(154, 205, 50));
		contentPane.add(panelJTree);
		panelJTree.setLayout(null);		
		
//***************** BOTONES GRANDES *******************		

		
		//BOTÓN CARGAR XML////////////////////////////////////////////////////////
		bt_XML = new JButton("");
		bt_XML.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				if(xmlFile == null){
					ventana = new JPanel();
					ventana.setBounds(getX(), getY(), getWidth(), getHeight());
					//------EL ARCHIVO /////////////////////////////////////////////	
					archivoX = new JFileChooser();
					archivoX.setBounds(getX(), getY(), getWidth() - 50, getHeight() - 50);
					ventana.add(archivoX);
					setContentPane(ventana);
					archivoX.addActionListener(new ActionListener() {						
						@Override
						public void actionPerformed(ActionEvent arg0) {							
							String command = arg0.getActionCommand();
							 if(command.equals(JFileChooser.APPROVE_SELECTION)){								 
								 xmlFile = archivoX.getSelectedFile();
								 readFile(xmlFile, XML);
								 setContentPane(contentPane);								 
							 }else if (command.equals(JFileChooser.CANCEL_SELECTION)){
								 setContentPane(contentPane);
							 }
						}
					});
				} else {
					readFile(xmlFile, XML);
					setContentPane(contentPane);
				}
				
			}
		});
		bt_XML.setSelectedIcon(new ImageIcon("assets\\img\\bt1-2.jpg"));
		bt_XML.setBackground(new Color(154, 205, 50));
		bt_XML.setIcon(new ImageIcon("assets\\img\\bt1.jpg"));
		bt_XML.setBounds(574, 23, 99, 89);
		bt_XML.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(bt_XML);
		
		//BOTÓN VALIDAR DTD
		bt_DTD = new JButton("");
		bt_DTD.setIcon(new ImageIcon("assets\\img\\bt2.jpg"));
		bt_DTD.setBackground(new Color(154, 205, 50));
		bt_DTD.setBounds(683, 23, 99, 89);
		bt_DTD.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bt_DTD.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				textArea_XML_BienFormado.setText(validator.XmlDtd(xmlFile.getAbsolutePath()));
				if(textArea_XML_BienFormado.getText().contains("no")){
					textArea_XML_BienFormado.setForeground(Color.RED);
				}
			}
		});
		contentPane.add(bt_DTD);
		
		//BOTÓN VALIDAR XSD
		bt_XSD = new JButton("");
		bt_XSD.setIcon(new ImageIcon("assets\\img\\bt3.jpg"));
		bt_XSD.setBackground(new Color(154, 205, 50));
		bt_XSD.setBounds(792, 23, 99, 89);
		bt_XSD.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bt_DTD.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				textArea_XML_BienFormado.setText(validator.XmlSchema(xmlFile.getAbsolutePath()));
				if(textArea_XML_BienFormado.getText().contains("no")){
					textArea_XML_BienFormado.setForeground(Color.RED);
				}
			}
		});
		contentPane.add(bt_XSD);
		

		
		
//***************** PESTAÑAS *******************		
		//PANEL PESTAÑAS
		PanelPestanias = new JTabbedPane(JTabbedPane.TOP);
		PanelPestanias.setBackground(new Color(154, 205, 50));
		PanelPestanias.setFont(new Font("Consolas", Font.BOLD, 19));
		PanelPestanias.setBounds(264, 132, 627, 494);
		contentPane.add(PanelPestanias);
		
		//--PESTAÑA 1************
		pestania_1 = new JPanel();
		pestania_1.setBackground(new Color(154, 205, 50));
		PanelPestanias.addTab("XML", null, pestania_1, null);
		pestania_1.setLayout(null);
		
		textArea_XML = new JTextArea();
		textArea_XML.setEditable(false);
		textArea_XML.setFont(new Font("Consolas", Font.PLAIN, 15));
		textArea_XML.setText("Aquí mostramos el código XML...");
		textArea_XML.setBounds(25, 56, 573, 305);
		textArea_XML.setMargin(new Insets(10, 10, 10, 10)); //margen de alrededor
		textArea_XML.setLineWrap(true); // q baje para abajo cuando llegues al final
		textArea_XML.setWrapStyleWord(true);  //para q no corte las palabra
		//pestania_1.add(textArea_XML);
		bt_Guardar = new JButton("GUARDAR");		
		bt_Guardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String text = textArea_XML.getText();
				try {
					PrintWriter writer = new PrintWriter(xmlFile);
					writer.print(text);
					writer.close();
					readFile(xmlFile, XML);
					setContentPane(contentPane);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});		
		
		bt_Guardar.setForeground(Color.WHITE);
		bt_Guardar.setFont(new Font("Consolas", Font.BOLD, 12));
		bt_Guardar.setBackground(Color.DARK_GRAY);
		bt_Guardar.setBounds(277, 11, 89, 23);
		pestania_1.add(bt_Guardar);
		
		scrollPane_XML = new JScrollPane();
		scrollPane_XML.setBounds(25, 56, 573, 305);
		scrollPane_XML.setViewportView(textArea_XML); //Añadir el Scroll al TextArea
		pestania_1.add(scrollPane_XML);
		
			
		textArea_XML_BienFormado = new JTextArea();
		textArea_XML_BienFormado.setEditable(false);
		textArea_XML_BienFormado.setBackground(Color.DARK_GRAY);
		textArea_XML_BienFormado.setForeground(Color.GREEN);
		textArea_XML_BienFormado.setFont(new Font("Consolas", Font.BOLD, 16));
		textArea_XML_BienFormado.setBounds(21, 366, 579, 68);
		textArea_XML_BienFormado.setMargin(new Insets(25, 50, 10, 10)); //margen de alrededor
		pestania_1.add(textArea_XML_BienFormado);
		
		
		//--PESTAÑA 2************
		pestania_2 = new JPanel();
		pestania_2.setBackground(new Color(154, 205, 50));
		PanelPestanias.addTab("XSLT", null, pestania_2, null);
		pestania_2.setLayout(null);
			
		
		textArea_XSLT = new JTextArea();
		textArea_XSLT.setText("Aquí mostramos el código XSLT...");
		textArea_XSLT.setFont(new Font("Consolas", Font.PLAIN, 15));
		textArea_XSLT.setBounds(25, 56, 573, 377);
		textArea_XSLT.setMargin(new Insets(10, 10, 10, 10)); //margen de alrededor
		textArea_XSLT.setLineWrap(true); // q baje para abajo cuando llegues al final
		textArea_XSLT.setWrapStyleWord(true);  //para q no corte las palabra
		//pestania_2.add(textArea_XSLT);
		
		scrollPane_XSLT = new JScrollPane();
		scrollPane_XSLT.setBounds(25, 56, 573, 377);
		scrollPane_XSLT.setViewportView(textArea_XSLT); //Añadir el Scroll al TextArea
		pestania_2.add(scrollPane_XSLT);
		bt_Editar = new JButton("GUARDAR");		
		bt_Editar.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String text = textArea_XSLT.getText();
				try {
					PrintWriter writer = new PrintWriter(xslFile);
					writer.print(text);
					writer.close();
					readFile(xslFile, XSLT);
					setContentPane(contentPane);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		
		bt_Editar.setForeground(Color.WHITE);
		bt_Editar.setFont(new Font("Consolas", Font.BOLD, 12));
		bt_Editar.setBackground(Color.DARK_GRAY);
		bt_Editar.setBounds(277, 11, 89, 23);
		pestania_2.add(bt_Editar);
		
		bt_Ejecutar = new JButton("EJECUTAR");
		bt_Ejecutar.setForeground(Color.WHITE);
		bt_Ejecutar.setBackground(Color.DARK_GRAY);
		bt_Ejecutar.setFont(new Font("Consolas", Font.BOLD, 12));
		bt_Ejecutar.setBounds(389, 11, 95, 23);
		bt_Ejecutar.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(generatedFile == null){
					ventana = new JPanel();
					ventana.setBounds(getX(), getY(), getWidth(), getHeight());
					//------EL ARCHIVO /////////////////////////////////////////////	
					archivoX = new JFileChooser();
					archivoX.setDialogTitle("Eliga un directorio para generar el fichero...");
					archivoX.setBounds(getX(), getY(), getWidth() - 50, getHeight() - 50);
					ventana.add(archivoX);
					setContentPane(ventana);
					archivoX.addActionListener(new ActionListener() {						
						@Override
						public void actionPerformed(ActionEvent arg0) {							
							String command = arg0.getActionCommand();
							 if(command.equals(JFileChooser.APPROVE_SELECTION)){
								 String outpath = "";
								 outpath = archivoX.getSelectedFile().getAbsolutePath(); 
								 generatedFile = XMLAddons.transformXMLWithXSLT(xslFile.getAbsolutePath(), outpath, doc);
								 readFile(generatedFile, FileGenerated);
								 setContentPane(contentPane);								 
							 }else if (command.equals(JFileChooser.CANCEL_SELECTION)){
								 setContentPane(contentPane);
							 }
						}
					});
				} else {
					readFile(generatedFile, FileGenerated);
					setContentPane(contentPane);
				}
			}
		});
		pestania_2.add(bt_Ejecutar);
		
		bt_Mostrar = new JButton("MOSTRAR");
		bt_Mostrar.setForeground(Color.WHITE);
		bt_Mostrar.setBackground(Color.DARK_GRAY);
		bt_Mostrar.setFont(new Font("Consolas", Font.BOLD, 12));
		bt_Mostrar.setBounds(509, 11, 89, 23);
		bt_Mostrar.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(xslFile == null){
					ventana = new JPanel();
					ventana.setBounds(getX(), getY(), getWidth(), getHeight());
					//------EL ARCHIVO /////////////////////////////////////////////	
					archivoX = new JFileChooser();
					archivoX.setBounds(getX(), getY(), getWidth() - 50, getHeight() - 50);
					ventana.add(archivoX);
					setContentPane(ventana);
					archivoX.addActionListener(new ActionListener() {						
						@Override
						public void actionPerformed(ActionEvent arg0) {							
							String command = arg0.getActionCommand();
							 if(command.equals(JFileChooser.APPROVE_SELECTION)){								 
								 xslFile = archivoX.getSelectedFile();
								 readFile(xslFile, XSLT);
								 setContentPane(contentPane);								 
							 }else if (command.equals(JFileChooser.CANCEL_SELECTION)){
								 setContentPane(contentPane);
							 }
						}
					});
				} else {
					readFile(xslFile, XSLT);
					setContentPane(contentPane);
				}
			}
		});
		pestania_2.add(bt_Mostrar);
		

		
		//--PESTAÑA 3************
		pestania_3 = new JPanel();
		pestania_3.setBackground(new Color(154, 205, 50));
		PanelPestanias.addTab("Expresiones", null, pestania_3, null);
		pestania_3.setLayout(null);
		
		textArea_Expresiones = new JTextArea();
		textArea_Expresiones.setBounds(28, 69, 570, 359);
		textArea_Expresiones.setText("Aquí mostramos el código...");
		textArea_Expresiones.setFont(new Font("Consolas", Font.PLAIN, 15));
		textArea_Expresiones.setMargin(new Insets(10, 10, 10, 10)); //margen de alrededor
		textArea_Expresiones.setLineWrap(true); // q baje para abajo cuando llegues al final
		textArea_Expresiones.setWrapStyleWord(true);  //para q no corte las palabra
		//pestania_3.add(textArea_Expresiones);
		
		scrollPane_Expresiones = new JScrollPane();
		scrollPane_Expresiones.setBounds(28, 69, 570, 359);
		scrollPane_Expresiones.setViewportView(textArea_Expresiones); //Añadir el Scroll al TextArea
		pestania_3.add(scrollPane_Expresiones);
		
		
		//--BOTON EJECUTAR PATH
		bt_EjecutarXpath = new JButton("EJECUTAR XPath");
		bt_EjecutarXpath.setBounds(28, 21, 147, 23);
		bt_EjecutarXpath.setForeground(Color.WHITE);
		bt_EjecutarXpath.setBackground(Color.DARK_GRAY);
		bt_EjecutarXpath.setFont(new Font("Consolas", Font.BOLD, 12));
		pestania_3.add(bt_EjecutarXpath);
		
		//--BOTON EJECUTAR XQUERY
		bt_EjecutarXquery = new JButton("EJECUTAR XQuery");
		bt_EjecutarXquery.setBounds(286, 21, 147, 23);
		bt_EjecutarXquery.setForeground(Color.WHITE);
		bt_EjecutarXquery.setBackground(Color.DARK_GRAY);
		bt_EjecutarXquery.setFont(new Font("Consolas", Font.BOLD, 12));
		pestania_3.add(bt_EjecutarXquery);
		
		//--BOTON EJECUTAR CARGAR XQUERY
		bt_CargarXquery = new JButton("CARGAR XQuery");
		bt_CargarXquery.setBounds(468, 21, 130, 23);
		bt_CargarXquery.setForeground(Color.WHITE);
		bt_CargarXquery.setBackground(Color.DARK_GRAY);
		bt_CargarXquery.setFont(new Font("Consolas", Font.BOLD, 12));
		pestania_3.add(bt_CargarXquery);
		

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//--PIE
		pie = new JLabel("2\u00BA DAM  |  2014");
		pie.setFont(new Font("Consolas", Font.PLAIN, 12));
		pie.setForeground(new Color(154, 205, 50));
		pie.setBounds(786, 642, 105, 13);
		contentPane.add(pie);

	}
	
	public void readFile(File f, String typeFile) {
		String text = "";
		try {
			Scanner scan = new Scanner(f);
			while(scan.hasNextLine()){
				text += scan.nextLine() + "\n";
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		switch(typeFile){
			case "XML": {
				textArea_XML.setText(text);
				textArea_XML.setEditable(true);
				if((doc = validator.XMLBienFormado(xmlFile.getAbsolutePath())) != null){									 
					 textArea_XML_BienFormado.setText("CÓDIGO BIEN FORMADO !!!");
					 chargeTree(doc);
				 } else {
					 textArea_XML_BienFormado.setForeground(Color.RED);
					 textArea_XML_BienFormado.setText("CÓDIGO MAL FORMADO !!!");
				 }
			}
				break;
			case "XSLT": {
				textArea_XSLT.setText(text);
				textArea_XSLT.setEditable(true);
			}
				break;
			case "GENERATED": {
				textArea_XSLT.setText("FICHERO GENERADO\n\n" + text);
				textArea_XSLT.setEditable(false);
			}
				break;
		}
		
	}
	
	public void chargeTree(Document doc) {
		NodeList nodes = doc.getChildNodes();
		Node rootNode = nodes.item(0);
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(rootNode.getNodeName());
		if(rootNode.hasChildNodes()){
			chargeLeafs(rootNode, root);
		}
		tree = new JTree(root);
		tree.setFont(new Font("Consolas", Font.PLAIN, 12));
		tree.setBounds(10, 11, 163, 472);
		panelJTree.add(tree);
	}
	
	public void chargeLeafs(Node node, DefaultMutableTreeNode parent) {
		NodeList nodes = node.getChildNodes();
		for(int i = 0; i < nodes.getLength(); i++){
			Node n = nodes.item(i);
			DefaultMutableTreeNode nodeTree = new DefaultMutableTreeNode(n.getNodeName());
			parent.add(nodeTree);
			if(n.hasChildNodes()){
				chargeLeafs(n, nodeTree);
			}
		}
	}
}
