/*-------------------------------------------------------------------------------------------------------------------*\
|  Copyright (C) 2014 PayPal                                                                                          |
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

import com.paypal.selion.annotations.WebTest;
import com.paypal.selion.platform.grid.Grid;
import com.mycompany.myproject.sample.MyAppHomePage;

import com.mycompany.myproject.utilities.server.TestServerUtils;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

/**
 * This sample demonstrates the Page Object Model that SeLion supports for interacting with web pages.
 * It leverages on the page classes that were created from selion code generator plugin.
 *
 */
public class UIFlowUsingSeLionPageObjectsTest {

    @BeforeClass
    public void startLocalServer () throws Exception {
        TestServerUtils.startServer();
    }

    @Test
    @WebTest
    public void myTest () {
        Grid.open(TestServerUtils.getAppURL());
        MyAppHomePage page = new MyAppHomePage(System.getProperty("locale"));
        page.getFirstNameTextField().type("Joe");
        page.getLastNameTextField().type("User");
        page.getEmailTextField().type("joeuser@foo.bar");

        page.getSubmitButton().clickAndExpect(ExpectedConditions.visibilityOf(page.getRetryAgainButton().getElement()));
        assertEquals(page.getFirstNameLabel().getAttribute("innerHTML"), "Joe");
        assertEquals(page.getLastNameLabel().getAttribute("innerHTML"), "User");
        assertEquals(page.getEmailLabel().getAttribute("innerHTML"), "joeuser@foo.bar");
    }

    @AfterClass
    public void shutdownLocalServer () throws Exception {
        TestServerUtils.stopServer();
    }

}
