//
//  Client.swift
//  UIoT-iOS
//
//  Created by camila oliveira on 07/03/17.
//  Copyright © 2017 UIoT. All rights reserved.
//

import UIKit
import SwiftyJSON

/**
 Example RAISe responses:
 
 "name": "Raspberry PI",
 "chipset": "AMD 790FX",
 "mac": "FF:FF:FF:FF:FF:FF",
 "serial": "C210",
 "processor": "Intel I3",
 "channel": "Ethernet",
 "client_time": 1317427200,
 "server_time": 1317987654
 }
 */

class UiotClient: NSObject {
    var name : String
    var chipset : String
    var mac : String
    var serial : String
    var processor: String
    var channel : String
    var client_time : Double
    var server_time : Double
    
    init(json : JSON) {
        name = json["name"].stringValue
        chipset = json["chipset"].stringValue
        mac = json["mac"].stringValue
        serial = json["serial"].stringValue
        processor = json["processor"].stringValue
        channel = json["channel"].stringValue
        client_time = json["client_time"].doubleValue
        server_time = json["server_time"].doubleValue
        
    }

}


