package xmlWorker;

import logic.Table;

import java.util.List;

public interface XmlWorker {
    void write(List<Table> tables, String path);
}
