
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.io.File;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tete
 */
public class jfrClientRMI extends javax.swing.JFrame {
    DefaultTableModel model = new DefaultTableModel();
    List<Library> librariesList = null;

    enum tags {name, ip, server, port, local, alias,  library};
    Boolean searchByBook = true;
    /**
     * 
     * Creates new form jfrClientRMI
     */
    public jfrClientRMI() {
        initComponents();
         NodeList nodeList = this.getLibrariesData("src/assets/LibrariesData.xml");
        if(this.librariesList == null){
            this.createLibraries(nodeList);
        }
        this.getLibrariesNames(nodeList);
        CreatingTable();
        this.setLocationRelativeTo(null);

        jButtonSearchByBook.setBorder(new LineBorder(new java.awt.Color(75,94,213), 3, true));
        jTable1.getTableHeader().setFont(new Font("Gujarati Sangam MN", Font.BOLD,12));
        jTable1.getTableHeader().setOpaque(false);
        jTable1.getTableHeader().setBackground(new Color(255,255,255));
        jTable1.setRowHeight(25);
    }

    /* Se calcula la fecha actual */
     private String calculateDate(){
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
     }
    
    /* Se crea la tabla de búsquedas de libros consultados, y se añaden los títulos del "header" */
    public void CreatingTable(){
        ArrayList<Object>columnName= new ArrayList<Object>();
        columnName.add("Título del libro");
        columnName.add("Biblioteca");
        for (Object column: columnName){
            model.addColumn(column);
        }
        this.jTable1.setModel(model);    
    }

    /* Se añaden los libros retornados por el servidor a los registros de la tabla de resultados */
    public void AddToTable(List<String> book, String selectedLibrary){
        ArrayList<Object[]>data= new ArrayList<Object[]>();
                   
        for (int i=0;i<book.size();i++){
            Object[] info = new Object[]{book.get(i),selectedLibrary};
            model.addRow(info);
        }
        jTable1.setModel(model);  
    }

    /* Se añaden al "JComboBox" los nombres de las librerías existentes */
    public void getLibrariesNames(NodeList nodeList){
        String tag = tags.name.toString();
        
        for(int i=0;i<nodeList.getLength();i++){
                 Node node = nodeList.item(i);
                 Element element = (Element) node;
                 jComboBoxLibraries.addItem(element.getElementsByTagName(tag).item(0).getTextContent());
             }
    }
    
    /* Se genera un número aleatorio, para el Id de la transacción */
    public int generateTransactionId(){
        return (int) (Math.random() * 999999) + 1;
    }
      
    /* Se crean las bibliotecas con la información almacenada en el archivo "LibrariesData.xml" */
    public void createLibraries(NodeList nodeList){
        this.librariesList =  new ArrayList<Library>();
        for(int i=0;i<nodeList.getLength();i++){
            Node node = nodeList.item(i);
            Element element = (Element) node;
            Library library = null;
            
            String name = element.getElementsByTagName(tags.name.toString()).item(0).getTextContent();
            String ip = element.getElementsByTagName(tags.ip.toString()).item(0).getTextContent();
            String server = element.getElementsByTagName(tags.server.toString()).item(0).getTextContent();
           // String alias = element.getElementsByTagName(tags.alias.toString()).item(0).getTextContent();      
            int  port = Integer.parseInt(element.getElementsByTagName(tags.port.toString()).item(0).getTextContent());

            /* Dependiendo de si la biblioteca seleccionada es local o remota, se crearán sus instancias */
            if (element.getElementsByTagName(tags.local.toString()).item(0).getTextContent().contains("true")){
              library = new LocalLibrary(name, ip, port, server, "Biblioteca A");
            }else{
              library = new RemoteLibrary(name, ip, port, server, "Biblioteca A");
            }
                 
            this.librariesList.add(library);  
        }
    }
    
    /* Retorna el contenido del archivo dado por parámetro */
    public NodeList getLibrariesData(String url){
        NodeList nodeList = null ;
            try{
             File file = new File(url);
             DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
             DocumentBuilder db = dbf.newDocumentBuilder();
             Document document = db.parse(file);
             document.getDocumentElement().normalize(); 
             nodeList = document.getElementsByTagName(tags.library.toString());
             return nodeList;
            }
            catch(Exception ex){
                 System.out.println(ex.getMessage());
            }
            return nodeList;
    }

    /* Se inicializan los componentes de la interfaz gráfica */
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToggleButton1 = new javax.swing.JToggleButton();
        jPanelPrimary = new javax.swing.JPanel();
        jLabClose = new javax.swing.JLabel();
        jLabMinimize = new javax.swing.JLabel();
        jPanelSide = new javax.swing.JPanel();
        jLabelTitle = new javax.swing.JLabel();
        jPanelBody = new javax.swing.JPanel();
        jPanelSearch = new javax.swing.JPanel();
        jTextFieldSearches = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jButtonSearches = new javax.swing.JButton();
        jComboBoxLibraries = new javax.swing.JComboBox<>();
        jButtonSearchByBook = new javax.swing.JButton();
        jButtonSearchByAuthor = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanelResults = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanelResultTexts = new javax.swing.JPanel();
        jLabelTitleResult = new javax.swing.JLabel();
        jLabelLibraryResult = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator1 = new javax.swing.JSeparator();

        jToggleButton1.setText("jToggleButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Bibliotecas del mundo");
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(java.awt.Color.darkGray);
        setUndecorated(true);
        setResizable(false);

        jPanelPrimary.setBackground(new java.awt.Color(255, 255, 255));

        jLabClose.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/cross.png"))); // NOI18N
        jLabClose.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabCloseMouseClicked(evt);
            }
        });

        jLabMinimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/minus.png"))); // NOI18N
        jLabMinimize.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabMinimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabMinimizeMouseClicked(evt);
            }
        });

        jPanelSide.setBackground(new java.awt.Color(75, 94, 213));

        javax.swing.GroupLayout jPanelSideLayout = new javax.swing.GroupLayout(jPanelSide);
        jPanelSide.setLayout(jPanelSideLayout);
        jPanelSideLayout.setHorizontalGroup(
            jPanelSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );
        jPanelSideLayout.setVerticalGroup(
            jPanelSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabelTitle.setFont(new java.awt.Font("Gujarati Sangam MN", 1, 24)); // NOI18N
        jLabelTitle.setForeground(new java.awt.Color(102, 102, 102));
        jLabelTitle.setText("Bienvenido a \"Bibliotecas del mundo\"");

        jPanelBody.setBackground(new java.awt.Color(255, 255, 255));

        jPanelSearch.setBackground(new java.awt.Color(255, 255, 255));
        jPanelSearch.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(75, 94, 213), 2));
        jPanelSearch.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextFieldSearches.setFont(new java.awt.Font("Gujarati Sangam MN", 0, 13)); // NOI18N
        jTextFieldSearches.setForeground(new java.awt.Color(102, 102, 102));
        jTextFieldSearches.setText("Busca un libro...");
        jTextFieldSearches.setBorder(null);
        jTextFieldSearches.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextFieldSearchesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTextFieldSearchesMouseEntered(evt);
            }
        });
        jTextFieldSearches.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSearchesActionPerformed(evt);
            }
        });
        jPanelSearch.add(jTextFieldSearches, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, 150, 30));

        jSeparator2.setForeground(new java.awt.Color(75, 94, 213));
        jPanelSearch.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 70, 200, 10));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/search.png"))); // NOI18N
        jPanelSearch.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, 40, 30));

        jButtonSearches.setFont(new java.awt.Font("Gujarati Sangam MN", 1, 14)); // NOI18N
        jButtonSearches.setForeground(new java.awt.Color(75, 94, 213));
        jButtonSearches.setText("Buscar ");
        jButtonSearches.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(75, 94, 213), 3));
        jButtonSearches.setContentAreaFilled(false);
        jButtonSearches.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButtonSearches.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButtonSearchesMouseMoved(evt);
            }
        });
        jButtonSearches.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchesActionPerformed(evt);
            }
        });
        jPanelSearch.add(jButtonSearches, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 120, 180, 40));

        jComboBoxLibraries.setFont(new java.awt.Font("Gujarati Sangam MN", 0, 13)); // NOI18N
        jComboBoxLibraries.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxLibrariesActionPerformed(evt);
            }
        });
        jPanelSearch.add(jComboBoxLibraries, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 80, 200, 35));

        jButtonSearchByBook.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/books.png"))); // NOI18N
        jButtonSearchByBook.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));
        jButtonSearchByBook.setContentAreaFilled(false);
        jButtonSearchByBook.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButtonSearchByBook.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/books-hover.png"))); // NOI18N
        jButtonSearchByBook.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButtonSearchByBookMouseMoved(evt);
            }
        });
        jButtonSearchByBook.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonSearchByBookMouseExited(evt);
            }
        });
        jButtonSearchByBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchByBookActionPerformed(evt);
            }
        });
        jPanelSearch.add(jButtonSearchByBook, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 138, 117));

        jButtonSearchByAuthor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/author.png"))); // NOI18N
        jButtonSearchByAuthor.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));
        jButtonSearchByAuthor.setContentAreaFilled(false);
        jButtonSearchByAuthor.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButtonSearchByAuthor.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/author-hover.png"))); // NOI18N
        jButtonSearchByAuthor.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButtonSearchByAuthorMouseMoved(evt);
            }
        });
        jButtonSearchByAuthor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonSearchByAuthorMouseExited(evt);
            }
        });
        jButtonSearchByAuthor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchByAuthorActionPerformed(evt);
            }
        });
        jPanelSearch.add(jButtonSearchByAuthor, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 20, 138, 117));

        jLabel3.setFont(new java.awt.Font("Gujarati Sangam MN", 0, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Buscar por autor");
        jPanelSearch.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 140, 138, -1));

        jLabel4.setFont(new java.awt.Font("Gujarati Sangam MN", 0, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Buscar por título");
        jPanelSearch.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 138, -1));

        jPanelResults.setBackground(new java.awt.Color(241, 241, 241));

        jLabel2.setFont(new java.awt.Font("Gujarati Sangam MN", 0, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Resultados de la búsqueda");

        jPanelResultTexts.setBackground(new java.awt.Color(255, 255, 255));

        jLabelTitleResult.setFont(new java.awt.Font("Gujarati Sangam MN", 0, 13)); // NOI18N
        jLabelTitleResult.setForeground(new java.awt.Color(102, 102, 102));
        jLabelTitleResult.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitleResult.setToolTipText("");
        jLabelTitleResult.setPreferredSize(new java.awt.Dimension(100, 100));

        jLabelLibraryResult.setFont(new java.awt.Font("Gujarati Sangam MN", 0, 13)); // NOI18N
        jLabelLibraryResult.setForeground(new java.awt.Color(102, 102, 102));
        jLabelLibraryResult.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelLibraryResult.setToolTipText("");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Título del libro", "Biblioteca"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setFocusable(false);
        jTable1.setRowHeight(10);
        jTable1.setSelectionBackground(new java.awt.Color(75, 94, 213));
        jTable1.setShowGrid(true);
        jTable1.setShowVerticalLines(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanelResultTextsLayout = new javax.swing.GroupLayout(jPanelResultTexts);
        jPanelResultTexts.setLayout(jPanelResultTextsLayout);
        jPanelResultTextsLayout.setHorizontalGroup(
            jPanelResultTextsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelResultTextsLayout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jLabelTitleResult, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelLibraryResult, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanelResultTextsLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 585, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );
        jPanelResultTextsLayout.setVerticalGroup(
            jPanelResultTextsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelResultTextsLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelResultTextsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelLibraryResult, javax.swing.GroupLayout.DEFAULT_SIZE, 15, Short.MAX_VALUE)
                    .addComponent(jLabelTitleResult, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jSeparator5.setForeground(new java.awt.Color(75, 94, 213));
        jSeparator5.setPreferredSize(new java.awt.Dimension(1, 12));
        jSeparator5.setSize(new java.awt.Dimension(10, 10));

        javax.swing.GroupLayout jPanelResultsLayout = new javax.swing.GroupLayout(jPanelResults);
        jPanelResults.setLayout(jPanelResultsLayout);
        jPanelResultsLayout.setHorizontalGroup(
            jPanelResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelResultsLayout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanelResultsLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanelResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelResultTexts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelResultsLayout.setVerticalGroup(
            jPanelResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelResultsLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelResultTexts, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelBodyLayout = new javax.swing.GroupLayout(jPanelBody);
        jPanelBody.setLayout(jPanelBodyLayout);
        jPanelBodyLayout.setHorizontalGroup(
            jPanelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBodyLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 582, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
            .addGroup(jPanelBodyLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelResults, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(19, 19, 19))
        );
        jPanelBodyLayout.setVerticalGroup(
            jPanelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBodyLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jPanelResults, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        jSeparator1.setForeground(new java.awt.Color(75, 94, 213));

        javax.swing.GroupLayout jPanelPrimaryLayout = new javax.swing.GroupLayout(jPanelPrimary);
        jPanelPrimary.setLayout(jPanelPrimaryLayout);
        jPanelPrimaryLayout.setHorizontalGroup(
            jPanelPrimaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPrimaryLayout.createSequentialGroup()
                .addComponent(jPanelSide, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanelPrimaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelPrimaryLayout.createSequentialGroup()
                        .addGap(202, 202, 202)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelPrimaryLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jPanelBody, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelPrimaryLayout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addComponent(jLabelTitle)))
                .addContainerGap(24, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPrimaryLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabMinimize)
                .addGap(18, 18, 18)
                .addComponent(jLabClose)
                .addGap(23, 23, 23))
        );
        jPanelPrimaryLayout.setVerticalGroup(
            jPanelPrimaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelSide, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPrimaryLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelPrimaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabClose)
                    .addComponent(jLabMinimize))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(jLabelTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelBody, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelPrimary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelPrimary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxLibrariesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxLibrariesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxLibrariesActionPerformed

    private void jButtonSearchByBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchByBookActionPerformed
        // TODO add your handling code here: 
             jButtonSearchByBook.setBorder(new LineBorder(new java.awt.Color(75,94,213), 3, true));
             jButtonSearchByAuthor.setBorder(new LineBorder(new java.awt.Color(255,255,255), 3, true));              
          this.searchByBook = true;
    }//GEN-LAST:event_jButtonSearchByBookActionPerformed

    private void jLabMinimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabMinimizeMouseClicked
        // TODO add your handling code here:
        this.setState(jfrClientRMI.ICONIFIED);
                
    }//GEN-LAST:event_jLabMinimizeMouseClicked

    private void jLabCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabCloseMouseClicked
        // TODO add your handling code here:
        int dialog = JOptionPane.YES_NO_OPTION;
        int result = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea salir?","Exit",dialog);
        if (result ==0){
            System.exit(0);
        }
    }//GEN-LAST:event_jLabCloseMouseClicked

    private void jButtonSearchByAuthorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchByAuthorActionPerformed
        // TODO add your handling code here:
         if (this.searchByBook == true){
             jButtonSearchByAuthor.setBorder(new LineBorder(new java.awt.Color(75,94,213), 3, true));
             jButtonSearchByBook.setBorder(new LineBorder(new java.awt.Color(255,255,255), 3, true));                   
        }
         else{
             jButtonSearchByAuthor.setBorder(new LineBorder(new java.awt.Color(255,255,255), 3, true));
             jButtonSearchByBook.setBorder(new LineBorder(new java.awt.Color(75,94,213), 3, true));
        }
         this.searchByBook = false;
        
    }//GEN-LAST:event_jButtonSearchByAuthorActionPerformed

    private void jButtonSearchByBookMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonSearchByBookMouseMoved
        // TODO add your handling code here:

    }//GEN-LAST:event_jButtonSearchByBookMouseMoved

    private void jButtonSearchByAuthorMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonSearchByAuthorMouseMoved
        // TODO add your handling code here:

    }//GEN-LAST:event_jButtonSearchByAuthorMouseMoved

    private void jButtonSearchByAuthorMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonSearchByAuthorMouseExited
        // TODO add your handling code here:
      
    }//GEN-LAST:event_jButtonSearchByAuthorMouseExited

    private void jButtonSearchByBookMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonSearchByBookMouseExited
        // TODO add your handling code here:
     
    }//GEN-LAST:event_jButtonSearchByBookMouseExited

    private void jTextFieldSearchesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSearchesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSearchesActionPerformed


    /* Acciona todo el proceso de búsqueda de un libro */
    private void jButtonSearchesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchesActionPerformed
        // TODO add your handling code here:
       
        List<Library> clients =  this.librariesList;
       int dialog = JOptionPane.INFORMATION_MESSAGE;
       String selectedLibrary = jComboBoxLibraries.getSelectedItem().toString();
        String bookSearch = jTextFieldSearches.getText();
       if (bookSearch.isEmpty()){
            JOptionPane.showMessageDialog(null, "Debe llenar los datos requeridos");
       }
       else{
           List<String> book = new ArrayList<String>(); ;
            for(int i = 0; i< clients.size(); i++){
                if (clients.get(i).name.contains(selectedLibrary)){
                    LibraryBuilder builder = new LibraryBuilder(clients.get(i));
                    int transactionId = this.generateTransactionId(); 
                    if (this.searchByBook == true){
                        String currentBook = builder.getBookByTitle(bookSearch, transactionId);
                        book.add(currentBook);
                    }
                    else{
                        book = builder.getBookByAuthor(bookSearch, transactionId);
                    }                   
                }
            } 
            if (book.isEmpty() || book.get(0).contains("null")){
                JOptionPane.showMessageDialog(null, "No se han encontrado resultados");  
            }else{              
                 AddToTable(book,selectedLibrary);
            }
       }  
    }//GEN-LAST:event_jButtonSearchesActionPerformed

    private void jButtonSearchesMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonSearchesMouseMoved
        // TODO add your handling code here:         
    }//GEN-LAST:event_jButtonSearchesMouseMoved

    private void jTextFieldSearchesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldSearchesMouseClicked
        // TODO add your handling code here:
        jTextFieldSearches.setText("");
    }//GEN-LAST:event_jTextFieldSearchesMouseClicked

    private void jTextFieldSearchesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldSearchesMouseEntered
        // TODO add your handling code here:
        jTextFieldSearches.setText("Busca un libro...");
    }//GEN-LAST:event_jTextFieldSearchesMouseEntered

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(jfrClientRMI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(jfrClientRMI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(jfrClientRMI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jfrClientRMI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new jfrClientRMI().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSearchByAuthor;
    private javax.swing.JButton jButtonSearchByBook;
    private javax.swing.JButton jButtonSearches;
    private javax.swing.JComboBox<String> jComboBoxLibraries;
    private javax.swing.JLabel jLabClose;
    private javax.swing.JLabel jLabMinimize;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelLibraryResult;
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JLabel jLabelTitleResult;
    private javax.swing.JPanel jPanelBody;
    private javax.swing.JPanel jPanelPrimary;
    private javax.swing.JPanel jPanelResultTexts;
    private javax.swing.JPanel jPanelResults;
    private javax.swing.JPanel jPanelSearch;
    private javax.swing.JPanel jPanelSide;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextFieldSearches;
    private javax.swing.JToggleButton jToggleButton1;
    // End of variables declaration//GEN-END:variables
}
