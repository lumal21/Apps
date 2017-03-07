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
 
{
    "code": 200,
    "clients": [
    {
    "name": "Raspberry PI",
    "chipset": "AMD 790FX",
    "mac": "FF:FF:FF:FF:FF:FF",
    "serial": "C210",
    "processor": "Intel I3",
    "channel": "Ethernet",
    "client_time": 1317427200,
    "server_time": 1317987654
    }
    ]
}
-------------------
{
    "code": 401,
    "message": "Unauthorized"
}
-------------------
    {
        "code": 403,
        "message": "Forbidden"
}
 */

 class UiotClient: NSObject {
    var clientType : ClientType
    var name : String = ""
    var chipset : String = ""
    var mac : String = ""
    var serial : String = ""
    var processor: String = ""
    var channel : String = ""
    var client_time : Double = 0
    var server_time : Double = 0
    
    init(json : JSON) {
        clientType = ClientType.init(rawValue: json["code"].intValue) ?? .NotIdentified
        if clientType == .Authorized{
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
    
    enum ClientType : Int {
        case Authorized = 200
        case Unauthorized = 401
        case Forbidden = 403
        case NotIdentified = 0
        
        var message : String{
            switch self {
            case .Authorized:
                return "Authorized"
            case .Forbidden:
                return "Forbidden"
            case .Unauthorized:
                return "Unauthorized"
            case .NotIdentified:
                return "Client not identified"
            }
        }
    }
}


