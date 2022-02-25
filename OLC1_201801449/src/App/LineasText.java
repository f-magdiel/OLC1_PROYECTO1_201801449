
package App;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
 
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.Document;
/**
 *
 * @author luis
 */
public class LineasText extends JPanel{
    
  JTextPane pane;
  JScrollPane scrollPane;
 
  public LineasText (){
    super ();
    setMinimumSize (new Dimension (30, 30));
    setPreferredSize (new Dimension (30, 30));
    setMinimumSize (new Dimension (30, 30));
    pane = new JTextPane (){ // se necesita pintar las lineas en el panel 
      public void paint (Graphics g)
      {
	super.paint (g);
	LineasText.this.repaint ();
      }
    };
    scrollPane = new JScrollPane (pane);
  }
 
  @Override
  public void paint (Graphics g){
    super.paint (g);
    int start =
      pane.viewToModel (scrollPane.getViewport ().getViewPosition ());
    int end =
      pane.viewToModel (new
		   Point (scrollPane.getViewport ().getViewPosition ().x +
			  pane.getWidth (),
			  scrollPane.getViewport ().getViewPosition ().y +
			  pane.getHeight ()));
    Document doc = pane.getDocument ();
    int startline = doc.getDefaultRootElement ().getElementIndex (start);
    int endline = doc.getDefaultRootElement ().getElementIndex (end)+1; //pinta la linea numero 1
    int fontHeight = g.getFontMetrics (pane.getFont ()).getHeight ();	// fuente
    
    for (int line = startline, y = 0; line <= endline;line++, y += fontHeight){
	g.drawString (Integer.toString (line), 0, y);
      }
  }
 
}
