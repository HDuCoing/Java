package nz.ac.massey.cs.sdc.parsers;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.io.StringWriter;
import java.util.List;
import java.util.logging.Logger;

@XmlRootElement
public class ReadXML extends TextInput {
    @XmlElement(required = true)
    protected String title;
    @XmlElement(required = true)
    protected String link;
    @XmlElement(required = true)
    protected String description;

    public static void main(String[] args) {
        ReadXML r = new ReadXML();
        r.read();
    }
    static Logger log = Logger.getLogger(ReadXML.class.getName());
    public void read() {
        try {
            // Set up unmarshall
            JAXBContext jc = JAXBContext.newInstance("nz.ac.massey.cs.sdc.parsers");
            Unmarshaller parser = jc.createUnmarshaller();
            // Parsed objects
            File file = new File("nzhrsscid_000000005.xml");
            Rss parsedFile = (Rss) parser.unmarshal(file);
            RssChannel parserChannel = parsedFile.getChannel();
            List<Object> parserItem = parserChannel.titleOrLinkOrDescription;
            // Marshaller
            Marshaller marshaller = jc.createMarshaller();
            StringWriter stringWriter = new StringWriter();
            for (Object s: parserItem) {
                marshaller.marshal(s, stringWriter );
            }
            System.out.println(stringWriter);

        } catch (JAXBException e) {
            log.info(String.valueOf(e));
        }
    }
}