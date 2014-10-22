if(typeof(Z) != "object") {var Z = {};};

(function () {
    var set_ns = function(ns, func) {
        var root = window;
        var ns_p = ns.split(".");
        var current;
        for(var i=0; i<ns_p.length; i++) {
            current = ns_p[i];
            if (!root[current]) {
                root[current] = {};
            }
            if (i == ns_p.length - 1) {
                root[current] = func;
                return;
            }
            root = root[current];
        }
    };

    var validate = function(arg) {
        this.form = $("#" + arg.form) || null;
        this.items = $("#" + arg.form + " :input") || [];
        this.rules = arg.rules || [];
        this.init(arg);
    };
    validate.prototype = {
        show_tip: function(el) {
            var self = this;
            var tip = self.rules[el.attr("name")].tip;
            if ( !$.trim("" + el.val()) ) {
                el.val(tip);
                el.addClass("input_blur");
            }
        },
        hide_tip: function(el) {
            var self = this;
            var tip = self.rules[el.attr("name")].tip;
            if (el.val() == tip) {
                el.val("");
                el.removeClass("input_blur");
            }
        },
        validate_empty: function(el) {
            var self = this;
            if ( !$.trim("" + el.val()) || el.val() == self.rules[el.attr("name")].tip ) {
                self.show_error(el, "不能为空");
                return false;
            }
            self.show_success(el);
            return true;
        },
        validate_equal: function(el) {
            var self = this;
            var equal_to = self.rules[el.attr("name")].equal.to;
            if (el.val() != $("#" + equal_to).val()) {
                self.show_error(el, self.rules[el.attr("name")].equal.message);
                return false;
            }
            self.show_success(el);
            return true;
        },
        validate_reg: function(el) {
            var self = this;
            var reg_to = new RegExp( self.rules[el.attr("name")].reg.to );
            var val = el.val();
            if ( !reg_to.test(val) ) {
                self.show_error(el, self.rules[el.attr("name")].reg.message);
                return false;
            }
            self.show_success(el);
            return true;
        },
        validate_remote: function(el) {
            var self = this;

            return true;
        },
        validate_all: function() {
            var self = this;
            var result = true;
            self.items.each(function(index, el){
                var el = $(el);
                var rule = self.rules[el.attr("name")] || null;
                if (!rule) return;
                var required = rule.required || null;
                var equal = rule.equal || null;
                var reg = rule.reg || null;
                if (!!required) {
                    if (!self.validate_empty(el)) {
                        result = false;
                        return true;
                    }
                }
                if (!!equal && !!equal.to) {
                    if (!self.validate_equal(el)) {
                        result = false;
                        return true;
                    }
                }
                if (!!reg && !!reg.to) {
                    if (!self.validate_reg(el)) result = false;
                }
            });
            return result;
        },
        show_error: function(el, message) {
            var self = this;
            var error = self.rules[el.attr("name")].error;
            error = $("#" + error) || null;
            if (!!error && !!message) {
                error.text(message);
                error.css("color", "#FF0000");
            }
        },
        show_success: function(el) {
            var self = this;
            var error = self.rules[el.attr("name")].error;
            error = $("#" + error) || null;
            if (!!error) {
                error.text("输入正确");
                error.css("color", "#00CC00");
            }
        },
        init: function(arg) {
            var self = this;
            if (!self.form) return;
            self.form.submit(function(e){
                return self.validate_all();
            });
            self.items.each(function(index, el){
                var el = $(el);
                var rule = self.rules[el.attr("name")] || null;
                if (!rule) return;
                var tip = rule.tip || null;
                var required = rule.required || null;
                var equal = rule.equal || null;
                var reg = rule.reg || null;
                var remote = rule.remote || null;
                if (!!tip) {
                    el.val(tip);
                    el.addClass("input_blur");
                }
                el.focus(function(e){
                    var that = $(this);
                    if (!!tip) self.hide_tip(that);
                });
                el.blur(function(e){
                    var that = $(this);
                    if (!!required) {
                        if (!self.validate_empty(that)) {
                            if (!!tip) self.show_tip(that);
                            return;
                        }
                    }
                    if (!!equal && !!equal.to) {
                        if (!self.validate_equal(that)) return;
                    }
                    if (!!reg && !!reg.to) {
                        if (!self.validate_reg(that)) return;
                    }
                    if (!!remote) {
                        if (!self.validate_remote(that)) return;
                    }
                });
            });
        }
    };
    set_ns("Z.validate", validate);
})();