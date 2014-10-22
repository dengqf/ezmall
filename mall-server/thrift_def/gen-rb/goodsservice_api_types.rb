#
# Autogenerated by Thrift Compiler (0.9.1)
#
# DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
#

require 'thrift'

module Goodsservice
  class TGoods
    include ::Thrift::Struct, ::Thrift::Struct_Union
    ID = 1
    NO = 2
    NAME = 3
    BRANDNO = 4
    BRANDNAME = 5
    CATEGORYNO = 6
    CATEGORYSTRUCT = 7
    CATEGORYSTRUCTNAME = 8
    MERCHANTNAME = 9
    MERCHANTCOMPANY = 10
    COSTPRICE = 11
    SELLPRICE = 12
    GIVESCORE = 13
    MARKETPRICE = 14
    EXPRESSPRICE = 15
    CURRENCYTYPE = 16
    STYLENO = 17
    SPECNAME = 18
    YEAR = 19
    UNIT = 20
    COUNTRY = 21
    CITY = 22
    GOODSDESC = 23
    GOODSPICTURE = 24
    THIRDLINK = 25
    THIRDNO = 26
    THIRDPICTURE = 27
    SUPPLIERCODE = 28
    STATUS = 29
    VALIDFLAG = 30
    CREATEDATE = 31
    UPDATEDATE = 32
    THIRDDOMAIN = 33
    THIRDID = 34
    THIRDCOL1 = 35
    THIRDCOL2 = 36

    FIELDS = {
      ID => {:type => ::Thrift::Types::STRING, :name => 'id'},
      NO => {:type => ::Thrift::Types::STRING, :name => 'no'},
      NAME => {:type => ::Thrift::Types::STRING, :name => 'name'},
      BRANDNO => {:type => ::Thrift::Types::STRING, :name => 'brandNo'},
      BRANDNAME => {:type => ::Thrift::Types::STRING, :name => 'brandName'},
      CATEGORYNO => {:type => ::Thrift::Types::STRING, :name => 'categoryNo'},
      CATEGORYSTRUCT => {:type => ::Thrift::Types::STRING, :name => 'categoryStruct'},
      CATEGORYSTRUCTNAME => {:type => ::Thrift::Types::STRING, :name => 'categoryStructName'},
      MERCHANTNAME => {:type => ::Thrift::Types::STRING, :name => 'merchantName'},
      MERCHANTCOMPANY => {:type => ::Thrift::Types::STRING, :name => 'merchantCompany'},
      COSTPRICE => {:type => ::Thrift::Types::DOUBLE, :name => 'costPrice'},
      SELLPRICE => {:type => ::Thrift::Types::DOUBLE, :name => 'sellPrice'},
      GIVESCORE => {:type => ::Thrift::Types::I32, :name => 'giveScore'},
      MARKETPRICE => {:type => ::Thrift::Types::DOUBLE, :name => 'marketPrice'},
      EXPRESSPRICE => {:type => ::Thrift::Types::DOUBLE, :name => 'expressPrice'},
      CURRENCYTYPE => {:type => ::Thrift::Types::STRING, :name => 'currencyType'},
      STYLENO => {:type => ::Thrift::Types::STRING, :name => 'styleNo'},
      SPECNAME => {:type => ::Thrift::Types::STRING, :name => 'specName'},
      YEAR => {:type => ::Thrift::Types::STRING, :name => 'year'},
      UNIT => {:type => ::Thrift::Types::STRING, :name => 'unit'},
      COUNTRY => {:type => ::Thrift::Types::STRING, :name => 'country'},
      CITY => {:type => ::Thrift::Types::STRING, :name => 'city'},
      GOODSDESC => {:type => ::Thrift::Types::STRING, :name => 'goodsDesc'},
      GOODSPICTURE => {:type => ::Thrift::Types::STRING, :name => 'goodsPicture'},
      THIRDLINK => {:type => ::Thrift::Types::STRING, :name => 'thirdLink'},
      THIRDNO => {:type => ::Thrift::Types::STRING, :name => 'thirdNo'},
      THIRDPICTURE => {:type => ::Thrift::Types::STRING, :name => 'thirdPicture'},
      SUPPLIERCODE => {:type => ::Thrift::Types::STRING, :name => 'supplierCode'},
      STATUS => {:type => ::Thrift::Types::STRING, :name => 'status'},
      VALIDFLAG => {:type => ::Thrift::Types::STRING, :name => 'validFlag'},
      CREATEDATE => {:type => ::Thrift::Types::I64, :name => 'createDate'},
      UPDATEDATE => {:type => ::Thrift::Types::I64, :name => 'updateDate'},
      THIRDDOMAIN => {:type => ::Thrift::Types::STRING, :name => 'thirdDomain'},
      THIRDID => {:type => ::Thrift::Types::STRING, :name => 'thirdId'},
      THIRDCOL1 => {:type => ::Thrift::Types::STRING, :name => 'thirdCol1'},
      THIRDCOL2 => {:type => ::Thrift::Types::STRING, :name => 'thirdCol2'}
    }

    def struct_fields; FIELDS; end

    def validate
    end

    ::Thrift::Struct.generate_accessors self
  end

  class TGoodsDto
    include ::Thrift::Struct, ::Thrift::Struct_Union
    GOODS = 1
    BRANDNAME = 2
    BRANDENGLISHNAME = 3
    STRUCTNAME = 4
    PROPMAP = 5

    FIELDS = {
      GOODS => {:type => ::Thrift::Types::STRUCT, :name => 'goods', :class => ::Goodsservice::TGoods},
      BRANDNAME => {:type => ::Thrift::Types::STRING, :name => 'brandName'},
      BRANDENGLISHNAME => {:type => ::Thrift::Types::STRING, :name => 'brandEnglishName'},
      STRUCTNAME => {:type => ::Thrift::Types::STRING, :name => 'structName'},
      PROPMAP => {:type => ::Thrift::Types::MAP, :name => 'propMap', :key => {:type => ::Thrift::Types::STRING}, :value => {:type => ::Thrift::Types::STRING}}
    }

    def struct_fields; FIELDS; end

    def validate
    end

    ::Thrift::Struct.generate_accessors self
  end

end
