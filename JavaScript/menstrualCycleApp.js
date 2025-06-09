const dayjs = require('dayjs')
const customParseFormat = require('dayjs/plugin/customParseFormat')
const advancedFormat = require('dayjs/plugin/advancedFormat')
dayjs.extend(customParseFormat)
dayjs.extend(advancedFormat)


function throwErrorsIfDateInputInvalidOrReturnFormattedDate(dateInput){
	let formattedDate = dayjs(dateInput, 'YYYY/MM/DD', true);

	if(!formattedDate.isValid()){
		throw new Error("Invalid date or format");
	}
	return formattedDate
}

function returnDayOfWeek(formattedDate){
	return formattedDate.format("dddd")
}

function periodFlowDays(dateInput, userAverageNoOfFlowDays){
	let formattedDate = throwErrorsIfDateInputInvalidOrReturnFormattedDate(dateInput)
	if(!Number.isInteger(userAverageNoOfFlowDays)){
		throw new Error("Enter a valid integer.")
	}
	return 'Your period flow will last from ' + returnDayOfWeek(formattedDate) + ', ' + formattedDate.format("DD MMMM, YYYY") + " to " + returnDayOfWeek(formattedDate.add(userAverageNoOfFlowDays, 'day')) + ', ' + formattedDate.add(userAverageNoOfFlowDays, 'day').format("DD MMMM, YYYY") + '.'
}

function findOvulationDay(nextPeriodStartDate){
	let ovulationDay = 14	
	return 'Ovulation day of current period is on ' + returnDayOfWeek(nextPeriodStartDate.subtract(ovulationDay, 'day')) + ', ' + nextPeriodStartDate.subtract(ovulationDay, 'day').format("DD MMMM, YYYY") + '.'
}


console.log(throwErrorsIfDateInputInvalidOrReturnFormattedDate("2025/06/09").format("YYYY/MM/DD"))
let format_date = throwErrorsIfDateInputInvalidOrReturnFormattedDate("2025/06/09")
console.log(returnDayOfWeek(format_date))
console.log(periodFlowDays("2025/06/09", 4))
