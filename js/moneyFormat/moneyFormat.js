/**
 * @file moneyFormat.js
 * @author Emanuel F. Oliveira
 * @email efiuza87@gmail.com
 * @date 2013-02-15
 * @version 1.0 (BETA)
 * @usage
 *
 * var price;
 *
 * // set return options
 * moneyFormat('config', {
 *     decimals          : 2,     // default
 *     decimalSeparator  : ',',   // default
 *     thousandsSeparator: '.',   // default
 *     symbol:           : 'R$',  // default
 *     postfixSymbol     : false, // default
 *     useSymbol         : true   // defaults to false
 * });
 *
 * // format number
 * price = moneyFormat(1499.989); // returns "R$ 1.499,99"
 *
 * // restore defaults
 * moneyFormat('reset');
 *
 */

(function (dict) {

	var digits = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9'];
	var conf, defs = {
		decimals          : 2,
		decimalSeparator  : ',',
		thousandsSeparator: '.',
		symbol            : 'R$',
		postfixSymbol     : false,
		useSymbol         : false
	};

	function init() {
		conf = { /* brand new object... */ };
		for (var p in defs)
			conf[p] = defs[p];
	}

	function config(opts) {
		var p, v;
		for (p in defs) {
			if (!(p in opts) || typeof opts[p] != typeof defs[p])
				continue;
			v = opts[p];
			// trim value if it is a string
			if (typeof v == 'string')
				v = v.replace(/^\s+|\s+$/g, '');
			// validate and adjust values
			switch (p) {
				case 'decimals':
					if (v >= 0 && v <= 3)
						v = v - (v % 1); // save integral part of v
					break;
				case 'thousandsSeparator':
				case 'decimalSeparator':
					if (v.length > 1)
						v = v.substr(0, 1);
					break;
			}
			conf[p] = v;
		}
	}

	function format(n) {
		var i, j, s, d, r = '';
		// negative symbol?
		if (s = n < 0)
			n = -n;
		// raise to power of j + 1 (last digit is used for rounding)
		for (i = 0, j = conf.decimals; i <= j; i++)
			n *= 10;
		// save integral part of n
		n = n - (n % 1);
		// round n discarding last decimal place
		d = n % 10; // move last digit to d
		if ((!s && d >= 5) || (s && d > 5))
			n += 10 - d;
		else
			n -= d;
		n /= 10;
		// build string
		i = 0, j = conf.decimals;
		while (n != 0 || j > 0) {
			if (j > 0 && i == j) {
				r = conf.decimalSeparator + r;
				i = 0, j = 0;
			} else if (i == 3) {
				r = conf.thousandsSeparator + r;
				i = 0;
			}
			d = n % 10;
			n = (n - d) / 10;
			r = digits[d] + r;
			i++;
		}
		// signal?
		if (s)
			r = '-' + r;
		// add symbol?
		if (conf.useSymbol) {
			if (conf.postfixSymbol)
				r = r + ' ' + conf.symbol;
			else
				r = conf.symbol + ' ' + r
		}
		return r;
	}

	// multiplexer
	function main(a, b) {
		// initialized?
		if (!conf)
			init();
		if (typeof a == 'number')
			return format(a);
		else if (a == 'config' && typeof b == 'object' && b != null)
			config(b);
		else if (a == 'reset')
			init();
		return null;
	}

	dict.moneyFormat = main;

})(window);
