// Newtons method to approximate positve real root of function
const places = process.argv[3] || 6;

/*
 * x(n+1) = x(n) - f(x(n))/f'(x(n))
 */

function nextIter(x, f, df) {
	if(df(x) == 0) throw new Error('Division by zero');
	return x - f(x)/df(x);
}

function estimate(x0, f, df, count) {
	let x = x0;
	count++;
	while(true) {
		if(count >= 100) {
			console.log('solution not found');
		}
		console.log('current iteration: x = ' + x);
		const xnew = nextIter(x, f, df).toFixed(places);
		if(xnew == x) {
			break;
		}
		x = xnew;
	}

	console.log('result: ' + x);
	return x;
}

function sqroot(num) {
	return estimate(1, (x)=>x*x-num, (x)=>2*x, 0);
}


if(process.argv.length < 2) {
	console.log('please enter an integer as argument');
} else {
	const input = process.argv[2];
	let num = parseInt(input);
	num = Math.abs(num);
	console.log('# integer input = ' + num);
	console.log('# decimal places = ' + places);
	console.log('#########################');
	if(!Number.isInteger(num)) {
		console.log('invalid arguments');
	} else {
		console.log('finding the square root of ' + num);
		const res = sqroot(num);
		const square = (res*res).toFixed(8);
		console.log('square of ' + res + ' is ' + square);
	}
}
