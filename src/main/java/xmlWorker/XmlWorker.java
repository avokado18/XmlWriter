package xmlWorker;

import logic.Table;

import javax.xml.transform.dom.DOMSource;
import java.util.List;

public interface XmlWorker {
    DOMSource write(List<Table> tables, String path);
}
