/**
 * The first thing to know about are types. The available types in Thrift are:
 *
 *  bool        Boolean, one byte
 *  byte        Signed byte
 *  i16         Signed 16-bit integer
 *  i32         Signed 32-bit integer
 *  i64         Signed 64-bit integer
 *  double      64-bit floating point value
 *  string      String
 *  binary      Blob (byte array)
 *  map<t1,t2>  Map from one type to another
 *  list<t1>    Ordered list of one type
 *  set<t1>     Set of unique elements of one type
 *
 * Did you also notice that Thrift supports C style comments?
 */

namespace rb goodsservice
namespace java com.ezmall.server.api

struct TGoods {
    1: string id,
    2: string no,
    3: string name,
    4: string brandNo,
    5: string brandName,
    6: string categoryNo,
    7: string categoryStruct,
    8: string categoryStructName,
    9: string merchantName,
    10: string merchantCompany, 
    11: double costPrice,
    12: double sellPrice,
    13: i32 giveScore,
    14: double marketPrice,
    15: double expressPrice,
    16: string currencyType,
    17: string styleNo,
    18: string specName,
    19: string year,
    20: string unit,
    21: string country,
    22: string city,
    23: string goodsDesc,
    24: string goodsPicture,
    25: string thirdLink,
    26: string thirdNo,
    27: string thirdPicture,
    28: string supplierCode,
    29: string status,
    30: string validFlag,
    31: i64 createDate,
    32: i64 updateDate,
    33: string thirdDomain,
    34: string thirdId,
    35: string thirdCol1,
    36: string thirdCol2,
}

struct TGoodsDto {
    1: TGoods goods,
    2: string brandName,
    3: string brandEnglishName,
    4: string structName,
    5: map<string, string> propMap,
}

service GoodsServiceApi {
	
	string getStr(1:string param1)
	
	string insertGoods(1:TGoodsDto goods)
	
	bool isGoodExists(1:string goodNo, 2:string thridDomain)
	
	bool checkThirdId(1:string thirdId, 2:string thridDomain)

}