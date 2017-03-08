//
//  UiotService.swift
//  UIoT-iOS
//
//  Created by camila oliveira on 07/03/17.
//  Copyright © 2017 UIoT. All rights reserved.
//

import UIKit
import SwiftyJSON
/**
 RAISe json responses

        {
        "name": "Get temp",
        "parameters": [
            {"name": "Temp",
            "type": "float"
            },
        }],
        "return_type": "float",
        "client_time": 1317427200,
        "server_time": 1317987654
        } */

class UiotService: NSObject {

    var name : String
    var parameters : [Parameter]
    var return_type : String
    var client_time : Double
    var server_time : Double
    
    init(json : JSON) {
        name = json["name"].stringValue
        return_type = json["return_type"].stringValue
        client_time = json["client_time"].doubleValue
        server_time = json["server_time"].doubleValue
        parameters = []
        for (_, js) in json["parameters"]{
            parameters.append(Parameter(json: js))
        }
    }
    
    init(name : String, return_type : String, client_time : Double, parameters : [Parameter]) {
        self.name = name
        self.return_type = return_type
        self.client_time = client_time
        self.parameters = parameters
        self.server_time = -1
    }
    
    
    class Parameter{
        var name : String
        var type : String
        init(json : JSON) {
            name = json["name"].stringValue
            type = json["type"].stringValue
        }
        
        init(name : String, type : String) {
            self.name = name
            self.type = type
        }
    }
}
