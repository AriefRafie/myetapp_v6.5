package ekptg.view.online;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;
import java.util.UUID;

import javax.imageio.ImageIO;

public class ImageVerification {
	
	private String value;

	  public ImageVerification(OutputStream out)
	    throws IOException
	  {
	    this(40, 144, out);
	  }

	  public ImageVerification(int height, int width, OutputStream out) throws IOException {
	    BufferedImage bimage = new BufferedImage(width, height, 1);
	    Random rand = new Random(System.currentTimeMillis());
	    Graphics2D g = bimage.createGraphics();

	    Color color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));

	    g.setColor(color.darker());
	    g.fillRect(0, 0, width, height);

	    g.setFont(new Font("arial", 1, 36));

	    this.value = UUID.randomUUID().toString().replace("-", "").substring(0, 5);

	    int w = g.getFontMetrics().stringWidth(this.value);
	    int d = g.getFontMetrics().getDescent();
	    int a = g.getFontMetrics().getMaxAscent();
	    int i;

	    int x = 0; int y = 0;

	    for (i = 0; i < height; i += 5)
	    {
	      g.setColor(new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)));
	      g.drawLine(x, y + i, width, y + i);
	    }

	    x = 0;
	    y = 0;

	    for (i = 0; i < height; i += 5)
	    {
	      g.setColor(new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)));
	      g.drawLine(x, y + d - i, width + w, height + d - i);
	    }

	    g.setColor(new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)).brighter().brighter());

	    x = width / 2 - w / 2;
	    y = height / 2 + a / 2 - 2;

	    AffineTransform fontAT = new AffineTransform();
	    int xp = x - 2;

	    for (int c = 0; c < this.value.length(); ++c)
	    {
	      int rotate = rand.nextInt(20);
	      fontAT.rotate((rand.nextBoolean()) ? Math.toRadians(rotate) : -Math.toRadians(rotate / 2));
	      Font fx = new Font("arial", 1, 36).deriveFont(fontAT);
	      g.setFont(fx);
	      String ch = String.valueOf(this.value.charAt(c));
	      int ht = rand.nextInt(3);

	      g.drawString(ch, xp, y + ((rand.nextBoolean()) ? -ht : ht));

	      xp += g.getFontMetrics().stringWidth(ch) + 2;
	    }

	    ImageIO.write(bimage, "png", out);

	    g.dispose();
	  }

	  public String getVerificationValue()
	  {
	    return this.value;
	  }

}
