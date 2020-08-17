
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

public class transformer {

    public static void main(String[] args) {
		
	    String xlstFile = args[0];
	    String input = args[1];
	    String output = args[2];

        StreamSource xlsCode = new StreamSource(new File(xlstFile));
        StreamSource inputSrc = new StreamSource(new File(input));
        StreamResult outSrc = new StreamResult(new File(output));
        TransformerFactory tf = TransformerFactory.newInstance();
        try {
            Transformer trans = tf.newTransformer(xlsCode);
            trans.transform(inputSrc, outSrc);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }
}
