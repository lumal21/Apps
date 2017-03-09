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
    fileprivate let host = "https://raise.homol.uiot.org"
    fileprivate let header = ["Content-Type": "application/json", "Accept" : "application/json"]
    struct apiEndPoint {
        static let client = "/client/"
        static let registerClient = "/client/register/"
        static let service = "/service/"
        static let registerService = "/service/register/"
        static let data = "/data/"
        static let registerData = "/data/register/"
    }
    
    
    
    
    
    
    
    
    
    
}

//all about GET DATA from server
//MARK:- GET
//extension DAO{
//    //MARK:- GET
//    
//    func get(data : UiotData, onSuccess: @escaping (String)->(), onFailure: @escaping (String)->()){
//        print("Registering DATA")
//        self.registerAll(data, onSuccess: {json in
//            onSuccess(json["message"].stringValue)
//        }, onFailure: {msg in
//            onFailure(msg)
//        })
//    }
//    
//}
//all about REGISTER into server
//MARK:- REGISTER
extension DAO{
    
    //MARK: PRIVATE
    
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
    
    ///On success it should have the Token
    private func registerAll(_ object : CustomDictionaryConvertible, endPoint : String, onSuccess: @escaping (JSON)->(), onFailure: @escaping (String)->()){
        let req = Alamofire.request(host+endPoint, method: HTTPMethod.post, parameters: object.toDictionary, encoding: URLEncoding.default, headers: header)
            //AlamofireRequest(endPoint: apiEndPoint.registerClient, parameters: object.toDictionary)
        
        responseJSON(dataRequest: req, onSuccess: {json in
            onSuccess(json)
        }, onFailure: {msg in
            onFailure(msg)
        })
    }
    
    //MARK: EXTERNAL USE
    ///OnSuccess should have the TOKEN for registered USER, but the token will be automatically seted up on the client anyways. If fails then Fail message is returned and nothing is setted up on client.token
    func register(client : UiotClient, onSuccess: @escaping (String)->(), onFailure: @escaping (String)->()){
        print("Registering CLIENT")
        /*
         "code": 200,
         "message": "Success",
         "token": "AAFFAAFFAAFFAAFF"
         */
        
        self.registerAll(client,endPoint: apiEndPoint.registerClient , onSuccess: {json in
            client.token = json["token"].stringValue
            onSuccess(json["token"].stringValue)
        }, onFailure: {msg in
            onFailure(msg)
        })
    }
    
    
    ///On success the message "success" is returned, and on failure the message with the reason is returned.
    func register(service : UiotService, onSuccess: @escaping (String)->(), onFailure: @escaping (String)->()){
        print("Registering SERVICE")
        self.registerAll(service,endPoint: apiEndPoint.registerService, onSuccess: {json in
            onSuccess(json["message"].stringValue)
        }, onFailure: {msg in
            onFailure(msg)
        })
    }
    
    
    ///On success the message "success" is returned, and on failure the message with the reason is returned.
    func register(data : UiotData, onSuccess: @escaping (String)->(), onFailure: @escaping (String)->()){
        print("Registering DATA")
        self.registerAll(data,endPoint: apiEndPoint.registerData, onSuccess: {json in
            onSuccess(json["message"].stringValue)
        }, onFailure: {msg in
            onFailure(msg)
        })
    }
    
    

}

