//
//  UiotModelProtocol.swift
//  UIoT-iOS
//
//  Created by camila oliveira on 08/03/17.
//  Copyright © 2017 UIoT. All rights reserved.
//

import UIKit

protocol  UiotModelProtocol {
    /**
     Must return the JSON representation of the object
     this method is called whenever is needed for registration. 
     see in: http://docs.uiot.org/raise
     to what values and what format is needed for your object
    */
    func jsonRepresentation () -> String
}
