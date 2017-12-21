package demo.model;

import java.util.Date;

public class Stock {

    private Long id;
    private Long productNum;
    private String stockName;
    private Date stockDate;
    private Date syncDate;
    private boolean sync;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductNum() {
        return productNum;
    }

    public void setProductNum(Long productNum) {
        this.productNum = productNum;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public Date getStockDate() {
        return stockDate;
    }

    public void setStockDate(Date stockDate) {
        this.stockDate = stockDate;
    }

    public Date getSyncDate() {
        return syncDate;
    }

    public void setSyncDate(Date syncDate) {
        this.syncDate = syncDate;
    }

    public boolean isSync() {
        return sync;
    }

    public void setSync(boolean sync) {
        this.sync = sync;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", productNum=" + productNum +
                ", stockName='" + stockName + '\'' +
                ", stockDate=" + stockDate +
                ", syncDate=" + syncDate +
                ", sync=" + sync +
                '}';
    }

    public Stock(Long id, Long productNum, String stockName, Date stockDate, Date syncDate, boolean sync) {
        this.id = id;
        this.productNum = productNum;
        this.stockName = stockName;
        this.stockDate = stockDate;
        this.syncDate = syncDate;
        this.sync = sync;
    }
}
