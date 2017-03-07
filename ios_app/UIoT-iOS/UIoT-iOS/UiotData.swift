//
//  UiotData.swift
//  UIoT-iOS
//
//  Created by camila oliveira on 07/03/17.
//  Copyright © 2017 UIoT. All rights reserved.
//

import UIKit
import SwiftyJSON
/**


    {
    "service_id": 15,
    "values": [
        {
        "value1": 35,
        "value2": 12
        }
    ],
    "tag": "Chip",
    "client_time": 1317427200,
    "server_time": 1317987654
    }
 
 */

class UiotData: NSObject {
    var service_id : Int
    var values : [(Int , Int)]
    var tag : String
    var server_time : String
    var client_time : String
    
    init(json : JSON) {
        service_id = json["service_id"].intValue
        tag = json["tag"].stringValue
        client_time = json["client_time"].stringValue
        server_time = json["server_time"].stringValue
        values = []
        for (_, js) in json["values"]{
            values.append((js["value1"].intValue, js["value2"].intValue))
        }
    }
    
}
