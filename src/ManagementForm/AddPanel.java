package ManagementForm;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class AddPanel {
	
	public void addItem (JPanel p, JComponent c, int y, int x, int width, int height,int align) {
		GridBagConstraints gb = new GridBagConstraints();
		
		gb.gridy= y;
		gb.gridx= x;
		gb.gridwidth = width;
		gb.gridheight= height;
		gb.anchor=align;
		gb.insets=new Insets(5,5,2,2);
		gb.fill=GridBagConstraints.BOTH;
		p.add(c,gb);
		

}
}
