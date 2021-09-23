/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import grafos.GrafoLogica;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 *
 * @author ssrs_
 */
public class Ventana extends JFrame implements ActionListener, MouseListener, MouseMotionListener {

    private JPanel P_Titulo, P_Ingreso, P_Grafo, P_Adyacencia, P_Arbol;
    private JLabel L_Titulo, L_Dibujo, L_Adyacencia, L_Origen, coordenadas;
    private JButton B_Ancho, B_Profundidad, B_Reinicio, B_Adyacencia, B_Grafo, B_Topografico, B_Limpiar, B_Cerrar;
    Color black, blueWhite, blue, blueheavy, blue2, white;
    private JComboBox Box_Origen;
    int x, y, contador;
    private GrafoLogica gl;
    private Graphics g;

    private int matrizAd[][];
    private JTextField leer[][];

    JScrollPane scrollMat = new JScrollPane(P_Adyacencia, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    public Ventana() {
        this.setTitle("Grafos");
        setLayout(null);
        setBounds(30, 30, 1350, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setBackground(blueWhite);
        gl = new GrafoLogica();
        setDatos();
        iniciarComponentes();
        this.setBackground(blue2);
    }

    private void iniciarComponentes() {
        Colores();
        Paneles();
        Etiquetas();
        Botones();
        Boxes();
    }

    private void Colores() {
        black = new Color(0, 0, 0);
        blueWhite = new Color(250, 250, 250);
        blue = new Color(250, 250, 250);
        blueheavy = new Color(250, 250, 250);
        blue2 = new Color(250, 250, 250);
        white = new Color(250, 250, 250);
    }

    private void Paneles() {
        P_Titulo = new JPanel();
        P_Titulo.setLayout(null);
        P_Titulo.setBounds(0, 0, 1350, 50);
        P_Titulo.setBackground(blue2);
        this.getContentPane().add(P_Titulo);

        P_Grafo = new JPanel();
        P_Grafo.setLayout(null);
        P_Grafo.setBounds(0, 55, 600, 480);
        P_Grafo.setBackground(white);
        P_Grafo.setBorder(BorderFactory.createLineBorder(Color.black));
        P_Grafo.addMouseListener(this);
        P_Grafo.addMouseMotionListener(this);
        P_Grafo.setVisible(true);

        P_Ingreso = new JPanel();
        P_Ingreso.setLayout(null);
        P_Ingreso.setBounds(0, 50, 600, 700);
        P_Ingreso.setBackground(blue);
        this.getContentPane().add(P_Ingreso);
        P_Ingreso.add(P_Grafo);

        P_Arbol = new JPanel();
        P_Arbol.setBounds(600, 400, 750, 350);
        P_Arbol.setBackground(blueWhite);
        add(P_Arbol).setVisible(true);

    }

    private void Etiquetas() {
        L_Titulo = new JLabel("Recorridos Grafo");
        L_Titulo.setForeground(black);
        L_Titulo.setFont(new Font("Impact", Font.PLAIN, 25));
        L_Titulo.setBounds(30, 15, 400, 20);
        P_Titulo.add(L_Titulo);

        L_Dibujo = new JLabel("Dibujar grafo");
        L_Dibujo.setForeground(black);
        L_Dibujo.setFont(new Font("Impact", Font.PLAIN, 20));
        L_Dibujo.setBounds(30, 10, 400, 40);
        P_Ingreso.add(L_Dibujo);

        L_Origen = new JLabel("Origen");
        L_Origen.setForeground(black);
        L_Origen.setFont(new Font("Impact", Font.PLAIN, 20));
        L_Origen.setBounds(50, 550, 200, 40);
        P_Ingreso.add(L_Origen).setVisible(false);

    }

    private void Botones() {
        B_Ancho = new JButton("Recorrido ancho");
        B_Ancho.setFont(new Font("Impact", Font.PLAIN, 15));
        B_Ancho.setBounds(50, 600, 200, 50);
        B_Ancho.setForeground(Color.white);
        B_Ancho.setBackground(Color.black);
        B_Ancho.addActionListener(this);
        P_Ingreso.add(B_Ancho).setVisible(false);

        B_Profundidad = new JButton("Recorrido profundidad");
        B_Profundidad.setFont(new Font("Impact", Font.PLAIN, 15));
        B_Profundidad.setBounds(300, 600, 200, 50);
        B_Profundidad.setForeground(Color.white);
        B_Profundidad.setBackground(Color.black);
        B_Profundidad.addActionListener(this);
        P_Ingreso.add(B_Profundidad).setVisible(false);

        B_Reinicio = new JButton("Reiniciar");
        B_Reinicio.setFont(new Font("Impact", Font.PLAIN, 15));
        B_Reinicio.setBounds(1225, 10, 100, 30);
        B_Reinicio.setForeground(Color.white);
        B_Reinicio.setBackground(Color.black);
        B_Reinicio.addActionListener(this);
        P_Titulo.add(B_Reinicio).setVisible(false);

        B_Adyacencia = new JButton("Lista adyacencia");
        B_Adyacencia.setFont(new Font("Impact", Font.PLAIN, 15));
        B_Adyacencia.setBounds(445, 5, 150, 40);
        B_Adyacencia.setForeground(Color.white);
        B_Adyacencia.setBackground(Color.black);
        P_Ingreso.add(B_Adyacencia).setVisible(false);
        B_Adyacencia.addActionListener(this);

        B_Limpiar = new JButton("Limpiar");
        B_Limpiar.setFont(new Font("Impact", Font.BOLD, 15));
        B_Limpiar.setBounds(280, 550, 100, 30);
        B_Limpiar.setForeground(Color.white);
        B_Limpiar.setBackground(Color.black);
        P_Ingreso.add(B_Limpiar).setVisible(false);
        B_Limpiar.addActionListener(this);
    }

    private void Boxes() {
        Box_Origen = new JComboBox();
        Box_Origen.setBounds(150, 550, 100, 30);
        P_Ingreso.add(Box_Origen).setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == B_Ancho) {
            System.out.println("Calculando ancho...");
            B_Reinicio.setVisible(true);
            B_Limpiar.setVisible(true);
            int dato = Integer.parseInt(String.valueOf(Box_Origen.getSelectedItem()));
            gl.crearRaiz(dato);
            gl.BFS(dato);
            DibujarArbol<Integer> vista = new DibujarArbol<>(gl.getArbolGeneral());
            g = P_Arbol.getGraphics();
            vista.dibujarComponente(g, 0, P_Arbol);

        }
        if (e.getSource() == B_Profundidad) {
            System.out.println("Calculando profundidad...");
            B_Reinicio.setVisible(true);
            B_Limpiar.setVisible(true);
            int dato = Integer.parseInt(String.valueOf(Box_Origen.getSelectedItem()));
            gl.crearRaiz(dato);
            gl.asigVisitado();
            gl.profundidad(dato);
            DibujarArbol<Integer> vista = new DibujarArbol<>(gl.getArbolGeneral());
            g = P_Arbol.getGraphics();
            vista.dibujarComponente(g, 100, P_Arbol);
        }
        if (e.getSource() == B_Reinicio) {
            System.out.println("Reinciando...");
            setDatos();
            Box_Origen.removeAllItems();
            P_Ingreso.repaint();
            P_Arbol.repaint();
            P_Adyacencia.setVisible(false);
            B_Limpiar.setVisible(false);
            B_Ancho.setVisible(false);
            B_Profundidad.setVisible(false);
            L_Origen.setVisible(false);
            Box_Origen.setVisible(false);
            matrizAd = null;
            leer = null;
        }
        if (e.getSource() == B_Adyacencia) {
            System.out.println("Adyacencia...");
            metodoMatriz();
            B_Reinicio.setVisible(true);
        }
        if (e.getSource() == B_Grafo) {
            System.out.println("Creando grafo...");
            int f = gl.getVertices().size();
            int c = gl.getVertices().size() - 1;
            guardarMatriz(f, c);
            llenarAdyacencia();
            imprimirAdyacencia();
            dibujarLineas();
            B_Ancho.setVisible(true);
            B_Profundidad.setVisible(true);
            L_Origen.setVisible(true);
            Box_Origen.setVisible(true);
        }
        if (e.getSource() == B_Limpiar) {
            P_Arbol.repaint();
        }
        if (e.getSource() == B_Cerrar) {
            System.exit(0);
        }
    }

    public void metodoMatriz() {
        int f, c;

        f = gl.getVertices().size();
        c = gl.getVertices().size() - 1;

        scrollMat.setBounds(600, 50, 750, 350);
        scrollMat.getHorizontalScrollBar().setCursor(new Cursor(Cursor.HAND_CURSOR));
        scrollMat.getHorizontalScrollBar().setBackground(Color.BLACK);
        scrollMat.getVerticalScrollBar().setCursor(new Cursor(Cursor.HAND_CURSOR));
        scrollMat.getVerticalScrollBar().setBackground(Color.BLACK);

        P_Adyacencia = new JPanel();
        scrollMat.setViewportView(P_Adyacencia);
        scrollMat.setVisible(true);
        P_Adyacencia.setLayout(null);
        P_Adyacencia.setBackground(blueWhite);
        P_Adyacencia.setPreferredSize(new Dimension((c * 100) + 75, (f * 60) + 75));

        add(scrollMat);

        P_Adyacencia.setVisible(true);

        L_Adyacencia = new JLabel("Lista Adyacencia");
        L_Adyacencia.setForeground(white);
        L_Adyacencia.setFont(new Font("Impact", Font.PLAIN, 25));
        L_Adyacencia.setBounds(30, 15, 400, 20);
        P_Adyacencia.add(L_Adyacencia).setVisible(true);

        matrizAd = new int[f][c];
        leer = new JTextField[c][f];

        for (y = 0; y < f; y++) {
            for (x = 0; x < c; x++) {
                leer[x][y] = new JTextField();
                P_Adyacencia.add(leer[x][y]);
                leer[x][y].setBounds(-50 + (82 * (x + 1)), 20 + (30 * (y + 1)), 50, 20);
                leer[x][y].setText("0");
                leer[x][y].setBackground(Color.white);
                leer[x][y].setForeground(Color.black);
            }
        }
        int posBoton = 30 + (30 * (y + 1));
        B_Grafo = new JButton("Crear grafo");
        B_Grafo.setFont(new Font("Impact", Font.PLAIN, 15));
        P_Adyacencia.add(B_Grafo).setVisible(true);
        B_Grafo.setForeground(Color.white);
        B_Grafo.setBackground(Color.black);
        B_Grafo.addActionListener(this);
        B_Grafo.setBounds(20, posBoton, 150, 40);

        for (int i = 0; i < f; i++) {
            coordenadas = new JLabel();
            P_Adyacencia.add(coordenadas);
            coordenadas.setText(Integer.toString(i + 1) + "->");
            coordenadas.setBounds(5, 20 + (30 * (i + 1)), 50, 20);
        }
    }

    public void guardarMatriz(int f, int c) {
        for (int i = 0; i < f; i++) {
            for (int j = 0; j < c; j++) {
                matrizAd[i][j] = Integer.parseInt(leer[j][i].getText());
                System.out.print(matrizAd[i][j] + " ");
            }
            System.out.println("");
        }
    }

    private void llenarAdyacencia() {
        int nVertices = gl.getVertices().size();
        ArrayList<Integer>[] Ady = new ArrayList[nVertices];
        for (int i = 0; i < nVertices; i++) {
            Ady[i] = new ArrayList();
            for (int j = 0; j < nVertices - 1; j++) {
                if (matrizAd[i][j] != 0) {
                    Ady[i].add(matrizAd[i][j]);
                }
            }
        }
        gl.setAdyacencia(Ady);
    }

    private void imprimirAdyacencia() {
        for (int i = 0; i < gl.getVertices().size(); i++) {
            System.out.print((i + 1) + "-> ");
            int lim = gl.getAdyacencia()[i].size();
            for (int j = 0; j < lim; j++) {
                System.out.print("[" + gl.getAdyacencia()[i].get(j) + "]");
            }
            System.out.println("");
        }
    }

    private void dibujarLineas() {
        g = P_Grafo.getGraphics();
        ArrayList<Integer>[] Ady = gl.getAdyacencia();
        for (int i = 0; i < Ady.length; i++) {
            for (int j = 0; j < Ady[i].size(); j++) {
                anadirLinea(i + 1, Ady[i].get(j), g);
            }
        }
    }

    public void setDatos() {
        contador = 0;
        x = 0;
        y = 0;
        gl.setDatos();
    }

    public void anadirCirculo(int x, int y, Graphics g) {
        contador++;
        Box_Origen.addItem(contador);
        g.setColor(Color.blue);
        g.drawOval(x, y, 20, 20);
        g.drawString(String.valueOf(contador), x + 3, y + 15);
        gl.getCoordVertices().add(x + "," + y);
        gl.getVertices().add(contador);

    }

    public void anadirLinea(int x, int y, Graphics g) {
        String[] cooIn, cooFn;
        int xini = 0, yini = 0, xfin = 0, yfin = 0;
        try {
            for (int i = 0; i < gl.getCoordVertices().size(); i++) {
                if (x == (i + 1)) {
                    cooIn = gl.getCoordVertices().get(i).split(",");
                    xini = Integer.parseInt(cooIn[0]);
                    yini = Integer.parseInt(cooIn[1]);
                } else if (y == (i + 1)) {
                    cooFn = gl.getCoordVertices().get(i).split(",");
                    xfin = Integer.parseInt(cooFn[0]);
                    yfin = Integer.parseInt(cooFn[1]);
                }
            }

            int xComp = xfin - xini;
            if (xComp > 30) {
                int yComp = yfin - yini;
                if (yComp > 30) {
                    yini += 20;
                } else if (yComp < -30) {
                    yfin += 20;
                } else {
                    yini += 5;
                    yfin += 5;
                }
                xini += 20;
            } else if (xComp < -30) {
                int yComp = yfin - yini;
                if (yComp > 30) {
                    yini += 20;
                } else if (yComp < -30) {
                    yfin += 20;
                } else {
                    yini += 5;
                    yfin += 5;
                }
                xfin += 20;
            } else {
                int yComp = yfin - yini;
                if (yComp > 30) {
                    yini += 20;
                } else if (yComp < -30) {
                    yfin += 20;
                } else {
                    yini += 5;
                    yfin += 5;
                }
                xini += 5;
                xfin += 5;
            }
            g.drawLine(xini, yini, xfin, yfin);
        } catch (IndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "No se encontro circulo");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 1) {
            B_Adyacencia.setVisible(true);
            System.out.println("Evento circulo");
            x = e.getX();
            System.out.println("x es: " + x);
            y = e.getY();
            g = P_Grafo.getGraphics();
            anadirCirculo(x, y, g);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
