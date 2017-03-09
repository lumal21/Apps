//
//  ViewController.swift
//  UIoT-iOS
//
//  Created by camila oliveira on 14/02/17.
//  Copyright © 2017 UIoT. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
       
        print("CLIENT")
        let client = UiotClient(name: "blaName", chipset: "blachipset", mac: "blamac", serial: "blaserial", processor: "blaprocessor", channel: "blachannel", client_time: 999999)

        DAO().register(client, onSuccess: { suc in
            print(suc)
        })
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
//        print("")
//        print("")
//        print("SERVICE")
//        let p1 = UiotService.Parameter(name: "parameter1", type: "tipo1")
//        let p2 = UiotService.Parameter(name: "parameter2", type: "tipo2")
//        print(UiotService(name: "sname", return_type: "sreturn", client_time: 7777, parameters: [p1, p2]))
//        
//        print("")
//        print("")
//        print("DATA")
//        print(UiotData(service_id: 22, tag: "datatag", values: [UiotData.Values(1, 1), UiotData.Values(2,2)], client_time: 8888))
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

