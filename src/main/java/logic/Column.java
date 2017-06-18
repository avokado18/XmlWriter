package logic;

import java.util.List;

public class Column {
    private String name;
    private Boolean isNullable;
    private String type;
    private Boolean isPrimary;
    private String foreignKeyTable;
    private String foreignKeyColumn;
    private Long countDistinctValues;
    private Long count;
    private Histogram histogram;
    private List listOfRareValues;

    @Override
    public String toString() {
        return "\n\tColumn{\n\t\t" +
                "name='" + name + '\'' +
                ", isNullable=" + isNullable +
                ", type='" + type + '\'' +
                ", isPrimary=" + isPrimary +
                ", foreignKey=" + foreignKeyTable +
                '.' + foreignKeyColumn +
                ", columnClassName='" + columnClassName + '\'' +
                ", countDistinctValues='" + countDistinctValues + '\'' +
                ", count=" + count +
                ", histogram=" + histogram +
                ", listOfRareValues=" + listOfRareValues +
                ", columnClassName='" + columnClassName + '\'' +
                "\n}";
    }

    public Long getCountDistinctValues() {
        return countDistinctValues;
    }

    public void setCountDistinctValues(Long countDistinctValues) {
        this.countDistinctValues = countDistinctValues;
    }

    public String getForeignKeyTable() {
        return foreignKeyTable;
    }

    public void setForeignKeyTable(String foreignKeyTable) {
        this.foreignKeyTable = foreignKeyTable;
    }

    public String getForeignKeyColumn() {
        return foreignKeyColumn;
    }

    public void setForeignKeyColumn(String foreignKeyColumn) {
        this.foreignKeyColumn = foreignKeyColumn;
    }

    private String columnClassName;


    public void setForeignKey(String foreignKeyTable, String foreignKeyColumn){
        this.foreignKeyColumn = foreignKeyColumn;
        this.foreignKeyTable = foreignKeyTable;
    }

    public String getColumnClassName() {
        return columnClassName;
    }

    public void setColumnClassName(String columnClassName) {
        this.columnClassName = columnClassName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isNullable() {
        return isNullable;
    }

    public void setNullable(Boolean aNull) {
        isNullable = aNull;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean isPrimary() {
        return isPrimary;
    }

    public void setPrimary(Boolean primary) {
        isPrimary = primary;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Histogram getHistogram() {
        return histogram;
    }

    public void setHistogram(Histogram histogram) {
        this.histogram = histogram;
    }

    public List getListOfRareValues() {
        return listOfRareValues;
    }

    public void setListOfRareValues(List listOfRareValues) {
        this.listOfRareValues = listOfRareValues;
    }
}