package com.mycompany.myproject.sample.simple;

import com.paypal.selion.platform.dataprovider.DataProviderFactory;
import com.paypal.selion.platform.dataprovider.DataResource;
import com.paypal.selion.platform.dataprovider.SeLionDataProvider;
import com.paypal.selion.platform.dataprovider.filter.SimpleIndexInclusionFilter;
import com.paypal.selion.platform.dataprovider.impl.FileSystemResource;

import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Iterator;

import static org.testng.Assert.assertTrue;

public class DemoXlsDataProviderTest {
    private static final String FILE_NAME = "src/test/resources/testdata/datafile.xls";
    private int numberOfRowsRead = 0;


    @DataProvider(name = "data")
    public Object[][] dataProvider () throws IOException{
        DataResource resource = new FileSystemResource(FILE_NAME, SimpleData.class);
        SeLionDataProvider dataProvider = DataProviderFactory.getDataProvider(resource);

        return dataProvider.getAllData();
    }

    @DataProvider(name = "filteredData")
    public Iterator<Object[]> filteredDataProvider () throws IOException{
        DataResource resource = new FileSystemResource(FILE_NAME, SimpleData.class);
        SeLionDataProvider dataProvider = DataProviderFactory.getDataProvider(resource);

        return dataProvider.getDataByFilter(new SimpleIndexInclusionFilter("1,3"));
    }

    @Test(dataProvider = "data")
    public void testData (SimpleData data) {
        showValues(data);
    }

    @Test(dataProvider = "filteredData")
    public void testFilteredData (SimpleData data) {
        showValues(data);
        numberOfRowsRead += 1;
    }

    @Test(dependsOnMethods = {"testFilteredData"})
    public void testHowManyRowsWereRead () {
        assertTrue(numberOfRowsRead == 2);
    }

    private void showValues(SimpleData data) {
        Reporter.log("Running test for " + data, true);
        assertTrue(data.getEmployeeId() != 0);
        assertTrue(data.getEmployeeName() != null);
    }
}
