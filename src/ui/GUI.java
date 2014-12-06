package ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

public class GUI extends JFrame{
	
	//ATRIBUTOS***************
	private JPanel contentPane;
	private JPanel panelJTree;
	
	private JLabel logo;
	private JTree tree;
	private JPanel panel;
	
	private JButton bt_XML;
	private JButton bt_DTD;
	private JButton bt_XSD;
	
	private JTabbedPane PanelPestanias;	
	private JPanel pestania_1;
	private JPanel pestania_2;
	
	private JTextArea textArea_XML;
	private JTextArea textArea_XML_BienFormado;
	private JScrollPane scrollPane_XML;
	
	private JTextArea textArea_XSLT;
	
	private JButton bt_Editar;
	private JButton bt_Ejecutar;
	private JButton bt_Mostrar;
	private JLabel pie;

	
	
	//CONSTRUCTOR*************
	public GUI() {
		

		
		//getContentPane().setLayout(null);
		
		//PANEL CONTENEDOR
		contentPane = new JPanel();
		contentPane.setBounds(0, 0, 934, 661);
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//LOGOTIPO
		logo = new JLabel("");
		logo.setIcon(new ImageIcon("E:\\DAW2014\\3DAM\\1TRIMESTRE\\PROYECTOS DAM\\Mis_Workspaces\\workspace_XMLanalize\\XML_analyze\\img\\logoXML.png"));
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
		
		//JTREE
		tree = new JTree();
		tree.setFont(new Font("Consolas", Font.PLAIN, 12));
		tree.setBounds(10, 11, 163, 472);
		panelJTree.add(tree);
		
		
//***************** BOTONES *******************		
		//BOTÓN CARGAR XML
		bt_XML = new JButton("");
		bt_XML.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		bt_XML.setSelectedIcon(new ImageIcon("E:\\DAW2014\\3DAM\\1TRIMESTRE\\PROYECTOS DAM\\Mis_Workspaces\\workspace_XMLanalize\\XML_analyze\\img\\bt1-2.jpg"));
		bt_XML.setBackground(new Color(154, 205, 50));
		bt_XML.setIcon(new ImageIcon("E:\\DAW2014\\3DAM\\1TRIMESTRE\\PROYECTOS DAM\\Mis_Workspaces\\workspace_XMLanalize\\XML_analyze\\img\\bt1.jpg"));
		bt_XML.setBounds(574, 23, 99, 89);
		bt_XML.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(bt_XML);
		
		//BOTÓN VALIDAR DTD
		bt_DTD = new JButton("");
		bt_DTD.setIcon(new ImageIcon("E:\\DAW2014\\3DAM\\1TRIMESTRE\\PROYECTOS DAM\\Mis_Workspaces\\workspace_XMLanalize\\XML_analyze\\img\\bt2.jpg"));
		bt_DTD.setBackground(new Color(154, 205, 50));
		bt_DTD.setBounds(683, 23, 99, 89);
		bt_DTD.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(bt_DTD);
		
		//BOTÓN VALIDAR XSD
		bt_XSD = new JButton("");
		bt_XSD.setIcon(new ImageIcon("E:\\DAW2014\\3DAM\\1TRIMESTRE\\PROYECTOS DAM\\Mis_Workspaces\\workspace_XMLanalize\\XML_analyze\\img\\bt3.jpg"));
		bt_XSD.setBackground(new Color(154, 205, 50));
		bt_XSD.setBounds(792, 23, 99, 89);
		bt_XSD.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
		//tabbedPane.addTab("Cita", new ImageIcon("C:\\Users\\ANDU\\Desktop\\img\\cita.png"), pestaña1, null);
		PanelPestanias.addTab("XML", null, pestania_1, null);
		pestania_1.setLayout(null);
		
		textArea_XML = new JTextArea();
		textArea_XML.setFont(new Font("Consolas", Font.PLAIN, 15));
		textArea_XML.setText("Aquí mostramos el código XML...");
		textArea_XML.setBounds(21, 21, 579, 319);
		textArea_XML.setMargin(new Insets(10, 10, 10, 10)); //margen de alrededor
		textArea_XML.setLineWrap(true); // q baje para abajo cuando llegues al final
		textArea_XML.setWrapStyleWord(true);  //para q no corte las palabra
		//pestania_1.add(textArea_XML);
		
		scrollPane_XML = new JScrollPane();
		scrollPane_XML.setBounds(21, 21, 579, 319);
		scrollPane_XML.setViewportView(textArea_XML); //Añadir el Scroll al TextArea
		pestania_1.add(scrollPane_XML);
		
		
		
		textArea_XML_BienFormado = new JTextArea();
		textArea_XML_BienFormado.setBackground(Color.DARK_GRAY);
		textArea_XML_BienFormado.setForeground(Color.GREEN);
		textArea_XML_BienFormado.setFont(new Font("Consolas", Font.BOLD, 16));
		textArea_XML_BienFormado.setText("CÓDIGO BIEN FORMADO !!!");
		textArea_XML_BienFormado.setBounds(21, 366, 579, 68);
		textArea_XML_BienFormado.setMargin(new Insets(25, 50, 10, 10)); //margen de alrededor
		pestania_1.add(textArea_XML_BienFormado);
		


		
		
		
		//--PESTAÑA 2************
		pestania_2 = new JPanel();
		pestania_2.setBackground(new Color(154, 205, 50));
		//tabbedPane.addTab("Cita", new ImageIcon("C:\\Users\\ANDU\\Desktop\\img\\cita.png"), pestaña1, null);
		PanelPestanias.addTab("XSLT", null, pestania_2, null);
		pestania_2.setLayout(null);
			
		
		textArea_XSLT = new JTextArea();
		textArea_XSLT.setText("Aquí mostramos el código XSLT...");
		textArea_XSLT.setBounds(25, 56, 573, 377);
		pestania_2.add(textArea_XSLT);
		
		
		bt_Editar = new JButton("EDITAR");
		bt_Editar.setForeground(Color.DARK_GRAY);
		bt_Editar.setFont(new Font("Consolas", Font.BOLD, 12));
		bt_Editar.setBackground(Color.DARK_GRAY);
		bt_Editar.setBounds(284, 11, 89, 23);
		pestania_2.add(bt_Editar);
		
		bt_Ejecutar = new JButton("EJECUTAR");
		bt_Ejecutar.setBackground(Color.DARK_GRAY);
		bt_Ejecutar.setFont(new Font("Consolas", Font.BOLD, 12));
		bt_Ejecutar.setBounds(395, 11, 89, 23);
		pestania_2.add(bt_Ejecutar);
		
		bt_Mostrar = new JButton("MOSTRAR");
		bt_Mostrar.setBackground(Color.DARK_GRAY);
		bt_Mostrar.setFont(new Font("Consolas", Font.BOLD, 12));
		bt_Mostrar.setBounds(509, 11, 89, 23);
		pestania_2.add(bt_Mostrar);
		
		pie = new JLabel("2\u00BA DAM  |  2014");
		pie.setFont(new Font("Consolas", Font.PLAIN, 12));
		pie.setForeground(new Color(154, 205, 50));
		pie.setBounds(789, 637, 105, 13);
		contentPane.add(pie);
		

		

		

		
		
		

		

		
	/**	
		
		//---FORMATOS PARA BOTÓN
		btPasarConsulta = new JButton("SIGUIENTE CONSULTA");
		btPasarConsulta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btPasarConsulta.setForeground(Color.WHITE);
		btPasarConsulta.setIcon(new ImageIcon("H:\\ProyectoJavaPen - PROYECTOfinal\\PROYECTOfinal_VETERINARIOS\\img\\siguiente.png"));
		btPasarConsulta.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		btPasarConsulta.setBackground(new Color(0, 0, 139));
		btPasarConsulta.setBounds(754, 0, 209, 35);
		contentPane.add(btPasarConsulta);
	**/	
		
		

		
	
	}
}
