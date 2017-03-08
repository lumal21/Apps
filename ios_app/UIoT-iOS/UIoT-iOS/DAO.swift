//
//  DAO.swift
//  UIoT-iOS
//
//  Created by camila oliveira on 07/03/17.
//  Copyright © 2017 UIoT. All rights reserved.
//

import UIKit

class DAO: NSObject {
    private let host = "http://raise.homol.uiot.org/"
    private var endPoint = ""
//    var token : String = ""
    init(endPoint : String) {
        self.endPoint = endPoint
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
    private func getURL() -> URL{
        if endPoint.characters.first == "/"{
            endPoint.remove(at: endPoint.startIndex)
        }
        return URL(string: host + endPoint)!
    }
 
    func register(_ Object : UiotModelProtocol){
        var request = URLRequest(url: getURL())
        request.httpMethod = "POST"
        
        let postString = Object.jsonRepresentation()
        request.httpBody = postString.data(using: .utf8)
        
        let task = URLSession.shared.dataTask(with: request){data,response,error in
            guard let data = data, error == nil else {                                                 // check for fundamental networking error
                print("error=\(error)")
                return
            }
            
            if let httpStatus = response as? HTTPURLResponse, httpStatus.statusCode != 200 {           // check for http errors
                print("statusCode should be 200, but is \(httpStatus.statusCode)")
                print("response = \(response)")
            }
            
            let responseString = String(data: data, encoding: .utf8)
            print("responseString = \(responseString)")
        }
        
        task.resume()
        
    }
    
    
    
    
    
    
    
    
    
    

}
















