//
//  DAO.swift
//  UIoT-iOS
//
//  Created by camila oliveira on 07/03/17.
//  Copyright © 2017 UIoT. All rights reserved.
//

import UIKit
import Alamofire

class DAO: NSObject {
    private let host = "http://raise.homol.uiot.org/"
    private var endPoint = ""
//    var token : String = ""
    init(_ endPoint : String) {
        self.endPoint = endPoint
    }

    private var url : String{
        if endPoint.characters.first == "/"{
            endPoint.remove(at: endPoint.startIndex)
        }
        return "\(host)\(endPoint)"
    }
 
    func register(_ Object : Any?){
        print (url)
        Alamofire.request(url).responseJSON { response in
            print(response.request)  // original URL request
            print(response.response) // HTTP URL response
            print(response.data)     // server data
            print(response.result)   // result of response serialization
            
            if let json = response.result.value {
                print("JSON: \(json)")
            }
        }

    }
    
    
    
    
    
    
    
    
    
    

}


/*
 //SERVICE
 
 curl -X POST --header 'Content-Type: application/json' --header 'Accept: application/json' -d '[ \
 { \
 "token": "AAFFAAFFAAFFAAFF", \
 "name": "Get temp", \
 "parameters": [ \
 { \
 "name": "Temp", \
 "type": "float" \
 } \
 ], \
 "return_type": "float", \
 "client_time": 1317427200 \
 } \
 ]' 'http://raw.githubusercontent.com/service/register/?type=true'
 
 //DATA
 curl -X POST --header 'Content-Type: application/json' --header 'Accept: application/json' -d '[ \
 { \
 "token": "AAFFAAFFAAFFAAFF", \
 "service_id": 15, \
 "values": [ \
 { \
 "value1": 35, \
 "value2": 12 \
 } \
 ], \
 "tag": "Chip", \
 "client_time": 1317427200 \
 } \
 ]' 'http://raw.githubusercontent.com/data/register/'
 
 //CLIENT
 curl -X POST --header 'Content-Type: application/json' --header 'Accept: application/json' -d '{ \
 "name": "Raspberry PI", \
 "chipset": "AMD 790FX", \
 "mac": "FF:FF:FF:FF:FF:FF", \
 "serial": "C210", \
 "processor": "Intel I3", \
 "channel": "Ethernet", \
 "client_time": 1317427200 \
 }' 'http://raw.githubusercontent.com/client/register/'
 */













