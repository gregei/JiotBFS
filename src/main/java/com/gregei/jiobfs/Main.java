package com.gregei.jiobfs;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Main {

    /**
     *  The binary maze
     */
    private int[][] graph = {
            { 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 1, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0 },
            { 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
            { 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
            { 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0 },
            { 1, 1, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0 },
            { 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0 },
            { 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0 },
            { 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 0, 1, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 }
    };

    public static void main(String[] args) {
        Main main = new Main();

        BFS b = new BFS();
        b.findPath(main.graph, 20, 20, new Point(0, 0), new Point(19, 19));

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("BFS");
                JPanel panel = new JPanel(){
                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);

                        int x = 0;
                        int y = 0;

                        Graphics2D g2 = (Graphics2D)g;

                        // Draw the grid filling coloring non traversal spots as black
                        // and leaving traversal spots as white.
                        for (int i = 0; i < main.graph.length; ++i) {
                            for (int j = 0; j < main.graph[i].length; ++j) {
                                if (main.graph[i][j] == 1) {
                                    g2.setStroke(new BasicStroke(1));
                                    g.setColor(Color.WHITE);
                                    g.fillRect(x, y, 20, 20);
                                    g.setColor(Color.BLACK);
                                    g.drawRect(x, y, 20, 20);
                                }
                                else {
                                    g2.setStroke(new BasicStroke(1));
                                    g.setColor(Color.GRAY);
                                    g.fillRect(x, y, 20, 20);
                                    g.setColor(Color.BLACK);
                                    g.drawRect(x, y, 20, 20);
                                }
                                x += 20;
                            }
                            x = 0;
                            y += 20;
                        }

                        ArrayList<Point> list =  b.points;

                        for (Point aList : list) {
                            g.setColor(Color.BLUE);
                            g.fillRect(aList.x * 20, aList.y * 20, 20, 20);
                            g.setColor(Color.RED);
                            g.drawRect(aList.x * 20, aList.y * 20, 20, 20);
                        }

                        // Draw origin as red and destination as green
                        int lastX = (main.graph.length - 1) * 20;
                        int lastY = (main.graph[0].length - 1) * 20;

                        g.setColor(Color.GREEN);
                        g.fillRect(lastX, lastY, 20, 20);
                        g.setColor(Color.BLACK);
                        g.drawRect(lastX, lastY, 20, 20);

                        g.setColor(Color.RED);
                        g.fillRect(0, 0, 20, 20);
                        g.setColor(Color.BLACK);
                        g.drawRect(0, 0, 20, 20);
                    }
                };

                panel.setSize(400, 400);
                frame.getContentPane().add(panel);
                frame.getContentPane().setPreferredSize(new Dimension(panel.getWidth(), panel.getHeight()));
                frame.pack();
                frame.setVisible(true);
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            }
        });
    }
}