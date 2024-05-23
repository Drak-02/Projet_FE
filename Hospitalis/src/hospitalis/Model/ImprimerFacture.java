/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitalis.Model;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;

/**
 *
 * @author badra
 */
public class ImprimerFacture implements Printable {
    private String facture;

    public ImprimerFacture(String facture) {
        this.facture = facture;
    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if (pageIndex > 0) {
            return NO_SUCH_PAGE;
        }

        Graphics2D g2d = (Graphics2D) graphics;
        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

        Font font = new Font("Monospaced", Font.PLAIN, 12); // You can adjust font settings as needed
        graphics.setFont(font);
        String[] lines = facture.split("\n");
        int y = 20;
        for (String line : lines) {
            graphics.drawString(line, 100, y);
            y += 15; // You can adjust line spacing as needed
        }

        return PAGE_EXISTS;
    }
}