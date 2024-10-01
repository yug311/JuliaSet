package Misc.juliaset;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;

public class JuliaSetProgram extends JPanel implements ActionListener, AdjustmentListener
{

	JFrame frame;
	Color color;
	int A, B, zoom, brightness, hue, saturation, moveX, moveY, degree;

	JScrollBar ABar, BBar, zoomBar, brightnessBar, hueBar, saturationBar, moveXBar, moveYBar, degreeBar;
	JPanel scrollPanel, labelPanel, bigPanel, buttonPanel;
	JLabel ALabel, BLabel, zoomLabel, brightnessLabel, hueLabel, saturationLabel, moveXLabel, moveYLabel, degreeLabel;
	JButton resetButton, randomButton, saveButton, switchButton;
	JFileChooser fileChooser;
	Boolean mandelbrot = false;

	BufferedImage image;

	public JuliaSetProgram()
	{
		frame = new JFrame("Julia Set Program");
		frame.add(this);
		frame.setSize(1000, 600);

		scrollPanel = new JPanel();
		GridLayout layout = new GridLayout(9,1);
		scrollPanel.setLayout(layout);

		ABar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 0, -2000, 2000);
		ABar.addAdjustmentListener(this);
		BBar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 0, -2000, 2000);
		BBar.addAdjustmentListener(this);
		moveXBar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 0, -2000, 2000);
		moveXBar.addAdjustmentListener(this);
		moveYBar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 0, -2000, 2000);
		moveYBar.addAdjustmentListener(this);
		zoomBar = new JScrollBar(JScrollBar.HORIZONTAL, 1, 0, 1, 100);
		zoomBar.addAdjustmentListener(this);
		brightnessBar = new JScrollBar(JScrollBar.HORIZONTAL, 100, 0, 0, 100);
		brightnessBar.addAdjustmentListener(this);
		hueBar = new JScrollBar(JScrollBar.HORIZONTAL, 1, 0, 0, 100);
		hueBar.addAdjustmentListener(this);
		saturationBar = new JScrollBar(JScrollBar.HORIZONTAL, 100, 0, 0, 100);
		saturationBar.addAdjustmentListener(this);
		degreeBar = new JScrollBar(JScrollBar.HORIZONTAL, 2, 0, 2, 4);
		degreeBar.addAdjustmentListener(this);

		scrollPanel.add(ABar);
		scrollPanel.add(BBar);
		scrollPanel.add(moveXBar);
		scrollPanel.add(moveYBar);
		scrollPanel.add(zoomBar);
		scrollPanel.add(brightnessBar);
		scrollPanel.add(hueBar);
		scrollPanel.add(saturationBar);
		scrollPanel.add(degreeBar);


		ALabel = new JLabel("A: " + (double)ABar.getValue()/1000);
		BLabel = new JLabel("B: " + (double)BBar.getValue()/1000);
		zoomLabel = new JLabel("Zoom: " + zoomBar.getValue());
		brightnessLabel = new JLabel("Brightness: " + brightnessBar.getValue());
		hueLabel = new JLabel("Hue: " + hueBar.getValue());
		saturationLabel = new JLabel("Saturation: " + saturationBar.getValue());
		moveXLabel = new JLabel("x: " + (double)moveXBar.getValue()/1000);
		moveYLabel = new JLabel("y: " + (double)moveYBar.getValue()/1000);
		degreeLabel = new JLabel("Degree: " + degreeBar.getValue());




		labelPanel = new JPanel();
		labelPanel.add(ALabel);
		labelPanel.add(BLabel);
		labelPanel.add(moveXLabel);
		labelPanel.add(moveYLabel);
		labelPanel.add(zoomLabel);
		labelPanel.add(brightnessLabel);
		labelPanel.add(hueLabel);
		labelPanel.add(saturationLabel);
		labelPanel.add(degreeLabel);


		labelPanel.setLayout(layout);
		labelPanel.setPreferredSize(new Dimension(100, 50));

		bigPanel = new JPanel();
		BorderLayout border = new BorderLayout();
		bigPanel.setLayout(border);
		bigPanel.add(labelPanel, BorderLayout.WEST);
		bigPanel.add(scrollPanel, BorderLayout.CENTER);

	//	frame.add(this);
		frame.add(bigPanel, BorderLayout.SOUTH);
	//	frame.setLayout(border);


		A = ABar.getValue();
		B = BBar.getValue();
		zoom = zoomBar.getValue();
		brightness = brightnessBar.getValue();
		hue = hueBar.getValue();
		saturation = saturationBar.getValue();
		moveX = moveXBar.getValue();
		moveY = moveYBar.getValue();
		degree = degreeBar.getValue();



		resetButton = new JButton("Reset");
		resetButton.addActionListener(this);
		buttonPanel = new JPanel();
		GridLayout layoutB = new GridLayout(4,1);
		buttonPanel.setLayout(layoutB);
		buttonPanel.add(resetButton);
		bigPanel.add(buttonPanel, BorderLayout.EAST);

		randomButton = new JButton("Random");
		randomButton.addActionListener(this);
		buttonPanel.add(randomButton);

		saveButton = new JButton("Save Image");
		saveButton.addActionListener(this);
		buttonPanel.add(saveButton);

		switchButton = new JButton("Switch to Mandelbrot Set");
		switchButton.addActionListener(this);
		buttonPanel.add(switchButton);



		String currDir=System.getProperty("user.dir");
		fileChooser=new JFileChooser(currDir);


		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		//g.setColor(color);
	//	g.fillRect(0, 0, frame.getWidth(), frame.getHeight());//delete later
		g.drawImage(drawJulia(), 0, 0, null);
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == resetButton)
		{
			ABar.setValue(0);
			BBar.setValue(0);
			zoomBar.setValue(1);
			brightnessBar.setValue(100);
			hueBar.setValue(1);
			saturationBar.setValue(100);
			moveXBar.setValue(0);
			moveYBar.setValue(0);
			degreeBar.setValue(2);


			A = 0;
			B = 0;
			zoom = 1;
			brightness = 100;
			hue = 1;
			saturation = 100;
			moveX = 0;
			moveY = 0;
			degree = 2;

		}

		if(e.getSource() == randomButton)
		{
			ABar.setValue((int)(Math.random()*4000) - 2000);
			BBar.setValue((int)(Math.random()*4000) - 2000);
			zoomBar.setValue(1);
			brightnessBar.setValue((int)(Math.random()*100));
			hueBar.setValue((int)(Math.random()*360));
			saturationBar.setValue((int)(Math.random()*100));
			degreeBar.setValue((int)(Math.random()*3) + 2);

			A = ABar.getValue();
			B = BBar.getValue();
			zoom = zoomBar.getValue();
			brightness = brightnessBar.getValue();
			hue = hueBar.getValue();
			saturation = saturationBar.getValue();
			degree = degreeBar.getValue();

		}

		if(e.getSource() == saveButton)
		{
			saveImage();
		}

		if(e.getSource() == switchButton)
		{
			if(!mandelbrot)
			switchButton.setText("Switch to Julia Set");

			else
			switchButton.setText("Switch to Mandelbrot Set");
			mandelbrot = !mandelbrot;
			repaint();
		}
	}

public void saveImage()
{
	if(image!=null)
	{
		FileFilter filter=new FileNameExtensionFilter("*.png","png");
		fileChooser.setFileFilter(filter);
		if(fileChooser.showSaveDialog(null)==JFileChooser.APPROVE_OPTION)
		{
			File file=fileChooser.getSelectedFile();
			try
			{
				String st=file.getAbsolutePath();
				if(st.indexOf(".png")>=0)
					st=st.substring(0,st.length()-4);
				ImageIO.write(image,"png",new File(st+".png"));
			}catch(IOException e)
			{
			}
		}
	}
}


	public void adjustmentValueChanged(AdjustmentEvent e)
	{
		if(e.getSource() == ABar)
		{
			A = ABar.getValue();
			ALabel.setText("A: " + (double)A/1000);
		}

		if(e.getSource() == BBar)
		{
			B = BBar.getValue();
			BLabel.setText("B: " + (double)B/1000);

		}

		if(e.getSource() == zoomBar)
		{
			zoom = zoomBar.getValue();
			zoomLabel.setText("Zoom: " + zoom);
		}

		if(e.getSource() == brightnessBar)
		{
			brightness = brightnessBar.getValue();
			brightnessLabel.setText("Brightness: " + brightness);
		}

		if(e.getSource() == hueBar)
		{
			hue = hueBar.getValue();
			hueLabel.setText("Hue: " + hue);
		}

		if(e.getSource() == saturationBar)
		{
			saturation = saturationBar.getValue();
			saturationLabel.setText("Saturation: " + saturation);
		}

		if(e.getSource() == moveXBar)
		{
			moveX = moveXBar.getValue();
			moveXLabel.setText("x: " + (double)moveX/1000);
		}

		if(e.getSource() == moveYBar)
		{
			moveY = moveYBar.getValue();
			moveYLabel.setText("y: " + (double)moveY/1000);
		}

		if(e.getSource() == degreeBar)
		{
			degree = degreeBar.getValue();
			degreeLabel.setText("Degree: " + degree);
		}
		repaint();
	}

	public BufferedImage drawJulia()
	{
		   	    int w = getWidth();
		        int h = getHeight();
		        image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

		        for (int x = 0; x < w; x++)
		        {
		            for (int y = 0; y < h; y++)
		            {
						if(mandelbrot)
						{
							drawMandelbrot(x, y, w, h);
						}

						if(!mandelbrot)
						drawJuliaSet(x, y, w, h);
		            }
		        }
        	return image;
	}

	public void drawMandelbrot(int x, int y, int w, int h)
	{
		               double zx = 0;
		               double zy = 0;
		               double cX = 1.5 * (x - w / 2) / (0.5 * zoom * w) + (double)moveX/1000;
            		   double cY = (y - h / 2) / (0.5 * zoom * h) + (double)moveY/1000;
		                int i = 300;

		                while (zx * zx + zy * zy < 6 && i > 0)
		                {
							if(degree == 2)
							{

		                  	 	double d = zx * zx - zy * zy + cX;
							 	zy = 2.0 * zx * zy + cY;
		                  	    zx = d;
							}


		                   	if(degree == 3)
						   	{
						   		double oldx = zx;
						   		double oldy = zy;

						   		double d = zx * zx - zy * zy;
						   		zy = 2.0 * zx * zy;
						   		zx = d;

						   		double e = zx * oldx - zy * oldy + cX;
						   		zy = zx * oldy + zy * oldx + cY;
						   		zx = e;
							}

		                   	if(degree == 4)
						   	{
						   		double oldx = zx;
						   		double oldy = zy;

						   		double d = zx * zx - zy * zy;
						   		zy = 2.0 * zx * zy;
						   		zx = d;

						   		double e = zx * oldx - zy * oldy;
						   		zy = zx * oldy + zy * oldx;
						   		zx = e;

						   		double f = zx * oldx - zy * oldy + cX;
						   		zy = zx * oldy + zy * oldx + cY;
						   		zx = f;
							}

							i--;
		                }
		                int c;

		               	if(i>0)
		               	{
					   		c = Color.HSBtoRGB(  (float)(i * hue) / 300, (float)saturation/100, (float)brightness/100);
						}
					   	else
							c = Color.HSBtoRGB((float)300 / i, 1, 0);

		                image.setRGB(x, y, c);

	}

	public void drawJuliaSet(int x, int y, int w, int h)
	{

		                double zx = 1.5 * (x - w / 2) / (0.5 * zoom * w) + (double)moveX/1000;
		                double zy = (y - h / 2) / (0.5 * zoom * h) + (double)moveY/1000;
		                int i = 300;

		                while (zx * zx + zy * zy < 6 && i > 0)
		                {
							if(degree == 2)
							{
		                  	  double d = zx * zx - zy * zy + (double)A/1000;
		                  	  zy = 2.0 * zx * zy + (double)B/1000;
		                  	  zx = d;
							}


		                   	if(degree == 3)
						   	{
						   		double oldx = zx;
						   		double oldy = zy;

						   		double d = zx * zx - zy * zy;
						   		zy = 2.0 * zx * zy;
						   		zx = d;

						   		double e = zx * oldx - zy * oldy + (double)A/1000;
						   		zy = zx * oldy + zy * oldx + (double)B/1000;
						   		zx = e;
							}

		                   	if(degree == 4)
						   	{
						   		double oldx = zx;
						   		double oldy = zy;

						   		double d = zx * zx - zy * zy;
						   		zy = 2.0 * zx * zy;
						   		zx = d;

						   		double e = zx * oldx - zy * oldy;
						   		zy = zx * oldy + zy * oldx;
						   		zx = e;

						   		double f = zx * oldx - zy * oldy + (double)A/1000;
						   		zy = zx * oldy + zy * oldx + (double)B/1000;
						   		zx = f;
							}

							i--;
		                }
		                int c;

		               	if(i>0)
		               	{
					   		c = Color.HSBtoRGB(  (float)(i * hue) / 300, (float)saturation/100, (float)brightness/100);
						}
					   	else
							c = Color.HSBtoRGB(0, 1, 0);

		                image.setRGB(x, y, c);

	}



	public static void main(String [] args)
	{
		JuliaSetProgram app = new JuliaSetProgram();
	}
}