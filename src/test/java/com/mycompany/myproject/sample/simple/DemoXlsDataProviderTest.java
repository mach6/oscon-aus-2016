/*-------------------------------------------------------------------------------------------------------------------*\
|  Copyright (C) 2016 PayPal                                                                                          |
|                                                                                                                     |
|  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance     |
|  with the License.                                                                                                  |
|                                                                                                                     |
|  You may obtain a copy of the License at                                                                            |
|                                                                                                                     |
|       http://www.apache.org/licenses/LICENSE-2.0                                                                    |
|                                                                                                                     |
|  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed   |
|  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for  |
|  the specific language governing permissions and limitations under the License.                                     |
\*-------------------------------------------------------------------------------------------------------------------*/

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
