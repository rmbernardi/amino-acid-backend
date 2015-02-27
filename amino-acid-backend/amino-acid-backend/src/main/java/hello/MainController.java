package hello;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicLong;

import javax.imageio.ImageIO;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController 
{

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Main greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Main(counter.incrementAndGet(),
                            String.format(template, name));
    }
    
    
    @RequestMapping(value = "/image", method = RequestMethod.GET, produces = "image/jpg")
    public @ResponseBody byte[] getFile()
    {
    	try
    	{
    		InputStream is = new FileInputStream("C:/Users/Richard/Desktop/orchidware.net/amino-acid-backend/amino-acid-backend/amino-acid-backend/test.jpg");    		
    		    		
    		BufferedImage img = ImageIO.read(is);
    		
    		ByteArrayOutputStream bao = new ByteArrayOutputStream();
    		
    		ImageIO.write(img,  "jpg",  bao);
    		
    		return bao.toByteArray();
    	}
    	catch (IOException e)
    	{
    		e.printStackTrace();
    		throw new RuntimeException(e);
    	}
    }
}
