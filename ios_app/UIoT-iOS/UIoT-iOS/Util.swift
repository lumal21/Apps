//
//  Util.swift
//  UIoT-iOS
//
//  Created by camila oliveira on 08/03/17.
//  Copyright © 2017 UIoT. All rights reserved.
//

import UIKit

class Util: NSObject {
    static func closeStringWithBraces(fromString  : String) -> String{

            var str = fromString
        
            str.characters[String(0)] = "{"
            str.characters[str.characters.count - 1] = "}"
        return str
        
    }
}
