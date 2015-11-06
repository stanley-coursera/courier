import Foundation
import SwiftyJSON

public enum UnionTyperef: Serializable {
    
    case StringMember(String)
    
    case IntMember(Int)
    case UNKNOWN$([String : AnyObject])
    
    public static func readJSON(json: JSON) throws -> UnionTyperef {
        let dict = json.dictionaryValue
        if let member = dict["string"] {
            return .StringMember(member.stringValue)
        }
        if let member = dict["int"] {
            return .IntMember(member.intValue)
        }
        if let unknownDict = json.dictionaryObject {
            return .UNKNOWN$(unknownDict)
        } else {
            throw ReadError.MalformedUnion
        }
    }
    public func writeData() -> [String: AnyObject] {
        switch self {
        case .StringMember(let member):
            return ["string": member];
        case .IntMember(let member):
            return ["int": member];
        case .UNKNOWN$(let dict):
            return dict
        }
    }
}

