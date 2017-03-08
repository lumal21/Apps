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
    var values : [Values]
    var tag : String
    var server_time : Double
    var client_time : Double
    
    init(json : JSON) {
        service_id = json["service_id"].intValue
        tag = json["tag"].stringValue
        client_time = json["client_time"].doubleValue
        server_time = json["server_time"].doubleValue
        values = []
        for (_, js) in json["values"]{
            values.append(Values(json : js))
        }
    }
    
    init(service_id : Int, tag : String, values : [Values], client_time : Double) {
        self.service_id = service_id
        self.values = values
        self.tag = tag
        self.client_time = client_time
        self.server_time = -1
    }
    
    
    override var description: String{
        let dict : [String : Any] = [
            "service_id": service_id,
            "values": values,
            "tag": tag,
            "client_time": client_time
        ]
        return "\(dict)"
    }
    
}

//INNER STRUCT VALUES
extension UiotData{
    struct Values : CustomStringConvertible {
        var value1 : Int
        var value2 : Int
        
        init(_ value1 : Int, _ value2 : Int) {
            self.value1 = value1
            self.value2 = value2
        }
        
        init(json : JSON) {
            self.value1 = json["value1"].intValue
            self.value2 = json["value2"].intValue
        }
        
        var description: String{
            let dict : [String : Any] = [
                "value1": value1,
                "value2": value2,
                ]
            
            return "\(dict)"
        }

        
    }
}
