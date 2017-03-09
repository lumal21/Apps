//
//  DAO.swift
//  UIoT-iOS
//
//  Created by camila oliveira on 07/03/17.
//  Copyright © 2017 UIoT. All rights reserved.
//

import UIKit
import Alamofire
import SwiftyJSON

class DAO: NSObject {
    private let host = "https://raise.homol.uiot.org"
    struct apiEndPoint {
        static let client = "/client/"
        static let registerClient = "/client/register/"
        static let service = "/service/"
        static let registerService = "/service/register/"
        static let data = "/data/"
        static let registerData = "/data/register/"
    }
    
    
    private func AlamofireRequest(endPoint : String, parameters : Parameters?) -> DataRequest{
        let header = ["Content-Type": "application/json", "Accept" : "application/json"]
        return Alamofire.request(host+endPoint, method: HTTPMethod.post, parameters: parameters, encoding: URLEncoding.default, headers: header)
    }
    
    private func responseJSON(dataRequest : DataRequest, onSuccess: @escaping (JSON)->(), onFailure: @escaping (String)->()){
        
        dataRequest.responseJSON{ response in
            print("Request sent to: \(response.request)")
//            print("Response: \(response.response)")
            
            if response.result.isSuccess  {
                if let json = response.result.value as? JSON {
                    if json["code"] == "200"{
                        print("JSON: \(json)")
                        //ON SUCCESS
                        onSuccess(json)
                    }else{
                        print("Request failed: \(json["message"].stringValue)")
                        onFailure(json["message"].stringValue)
                    }
                }else{
                    print("Failed to return JSON: returned \(response.response)")
                    onFailure("Failed to return JSON")
                }
            }else{
                print("Request Failed: \(response.result.error)")
                onFailure("Request Failed: \(response.result.error)")
            }
            
            
        }
        
    }
    
    
    ///On success it should return the Token
    func register(_ client : UiotClient, onSuccess: @escaping (String)->()){
        
        let req = AlamofireRequest(endPoint: apiEndPoint.registerClient, parameters: client.toDictionary)
        
        responseJSON(dataRequest: req, onSuccess: {json in
            print(json)
        }, onFailure: {_ in
            print("do something")
        })
        
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













