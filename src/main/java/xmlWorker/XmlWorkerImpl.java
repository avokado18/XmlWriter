package xmlWorker;

import logic.Column;
import logic.Histogram;
import logic.Table;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;


public class XmlWorkerImpl implements XmlWorker{
    public void write(List<Table> tables, String path){
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            Document doc = factory.newDocumentBuilder().newDocument();

            for (Table table : tables){
                doc.appendChild(getTableElement(doc, table));
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(path));
            transformer.transform(source, result);

        } catch (ParserConfigurationException ex){
            ex.printStackTrace();
        } catch (TransformerException tex){
            tex.printStackTrace();
        }
    }

    private Element getHistogramElement(Document doc, Histogram histogram){

        Element histogramElement = doc.createElement("histogram");

        Element histogramMin = doc.createElement("min");
        histogramMin.appendChild(doc.createTextNode(histogram.getMin().toString()));
        histogramElement.appendChild(histogramMin);

        Element histogramMax = doc.createElement("max");
        histogramMax.appendChild(doc.createTextNode(histogram.getMax().toString()));
        histogramElement.appendChild(histogramMax);

        Element histogramStep = doc.createElement("step");
        histogramStep.appendChild(doc.createTextNode(histogram.getStep().toString()));
        histogramElement.appendChild(histogramStep);

        Element histogramStepCount = doc.createElement("stepCount");
        histogramStepCount.appendChild(doc.createTextNode(histogram.getStepCount().toString()));
        histogramElement.appendChild(histogramStepCount);

        Element histogramFrequencies = doc.createElement("frequencies");
        for (Long item : histogram.getFrequencies()){
            Element value = doc.createElement("value");
            value.appendChild(doc.createTextNode(item.toString()));
            histogramFrequencies.appendChild(value);
        }
        histogramElement.appendChild(histogramFrequencies);

        Element expectetion = doc.createElement("expectetion");
        expectetion.appendChild(doc.createTextNode(histogram.getExpectation().toString()));
        histogramElement.appendChild(expectetion);

        Element dispersion = doc.createElement("dispersion");
        dispersion.appendChild(doc.createTextNode(histogram.getDispersion().toString()));
        histogramElement.appendChild(dispersion);

        return histogramElement;
    }

    private Element getColumnElement(Document doc, Column column){
        Element columnElement = doc.createElement("column");

        Element columnName = doc.createElement("name");
        columnName.appendChild(doc.createTextNode(column.getName()));
        columnElement.appendChild(columnName);

        Element isNullable = doc.createElement("isNullable");
        isNullable.appendChild(doc.createTextNode(column.isNullable().toString()));
        columnElement.appendChild(isNullable);

        Element type = doc.createElement("type");
        type.appendChild(doc.createTextNode(column.getType()));
        columnElement.appendChild(type);

        Element isPrimary = doc.createElement("isPrimary");
        isPrimary.appendChild(doc.createTextNode(column.isPrimary().toString()));
        columnElement.appendChild(isPrimary);

        if (column.getForeignKeyTable() != null){
            Element foreignKeyTable = doc.createElement("foreignKeyTable");
            foreignKeyTable.appendChild(doc.createTextNode(column.getForeignKeyTable()));
            columnElement.appendChild(foreignKeyTable);
        }

        if (column.getForeignKeyColumn() != null){
            Element foreignKeyColumn = doc.createElement("foreignKeyColumn");
            foreignKeyColumn.appendChild(doc.createTextNode(column.getForeignKeyColumn()));
            columnElement.appendChild(foreignKeyColumn);
        }

        Element countDistinctValues = doc.createElement("countDistinctValues");
        countDistinctValues.appendChild(doc.createTextNode(column.getCountDistinctValues().toString()));
        columnElement.appendChild(countDistinctValues);

        Element count = doc.createElement("count");
        count.appendChild(doc.createTextNode(column.getCount().toString()));
        columnElement.appendChild(count);

        Element listOfRareValues = doc.createElement("listOfRareValues");
        for (Object value : column.getListOfRareValues()){
            Element valueElement = doc.createElement("value");
            valueElement.appendChild(doc.createTextNode(value.toString()));
            listOfRareValues.appendChild(valueElement);
        }
        columnElement.appendChild(listOfRareValues);

        columnElement.appendChild(getHistogramElement(doc, column.getHistogram()));

        return columnElement;
    }

    private Element getTableElement(Document doc, Table table){
        Element tableElement = doc.createElement("table");

        Element tableName = doc.createElement("name");
        tableName.appendChild(doc.createTextNode(table.getName()));
        tableElement.appendChild(tableName);

        Element columnCount = doc.createElement("columnCount");
        columnCount.appendChild(doc.createTextNode(table.getColumnCount().toString()));
        tableElement.appendChild(columnCount);

        Element rowCount = doc.createElement("rowCount");
        columnCount.appendChild(doc.createTextNode(table.getRowCount().toString()));
        tableElement.appendChild(rowCount);

        //add columns
        for (Map.Entry<String, Column> column : table.getColumns().entrySet()){
            tableElement.appendChild(getColumnElement(doc, column.getValue()));
        }
        return tableElement;
    }


//    @RequestMapping(path = "/download", method = RequestMethod.GET)
//    public ResponseEntity<InputStreamResource> download() throws IOException {
//
//        File file = new File("C:\\Users\\1\\Desktop\\Prog\\ДИПЛОМ\\Sasha\\src\\main\\resources\\file.xml");
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
//        headers.add("Pragma", "no-cache");
//        headers.add("Expires", "0");
//
//        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
//
//        return ResponseEntity.ok()
//                .headers(headers)
//                .contentLength(file.length())
//                .contentType(MediaType.parseMediaType("application/octet-stream"))
//                .body(resource);
//    }
}
