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

import org.openqa.selenium.remote.Command;
import org.openqa.selenium.remote.DriverCommand;

import com.paypal.selion.platform.grid.EventListener;
import com.paypal.selion.reports.runtime.SeLionReporter;

public class DemoEventFiringListener implements EventListener {
    public void beforeEvent(Command command) {
        if (command.getName().equalsIgnoreCase(DriverCommand.CLICK)) {
            System.out.println("Before click");
        }
    }

    public void afterEvent(Command command) {
        if (command.getName().equalsIgnoreCase(DriverCommand.CLICK)) {
            System.out.println("After click");
        }
    }
}
