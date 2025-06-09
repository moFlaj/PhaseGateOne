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
// Ovulation day occurs 14 days before start of next period.	
	return 'Ovulation day of current period is on ' + returnDayOfWeek(nextPeriodStartDate.subtract(ovulationDay, 'day')) + ', ' + nextPeriodStartDate.subtract(ovulationDay, 'day').format("DD MMMM, YYYY") + '.'
}

function identifyFertileWindow(ovulationDay){
	let fertileWindowBefore = throwErrorsIfDateInputInvalidOrReturnFormattedDate(ovulationDay).subtract(5, 'day')
	let fertileWindowAfter = throwErrorsIfDateInputInvalidOrReturnFormattedDate(ovulationDay).add(1, 'day')
// Fertile window begins five days before ovulation day and ends, one day after ovulation day.
	return 'Fertile window will open on ' + returnDayOfWeek(fertileWindowBefore) + ', ' + fertileWindowBefore.format("DD MMMM, YYYY") + ' and closes on ' + returnDayOfWeek(fertileWindowAfter) + ', ' + fertileWindowAfter.format("DD MMMM, YYYY") + '.'

}

console.log(throwErrorsIfDateInputInvalidOrReturnFormattedDate("2025/06/09").format("YYYY/MM/DD"))
let format_date = throwErrorsIfDateInputInvalidOrReturnFormattedDate("2025/06/09")
console.log(returnDayOfWeek(format_date))
console.log(periodFlowDays("2025/06/09", 4))
console.log(nextPeriodStartDate("2025/06/09", 28))
let nextPeriodDate = throwErrorsIfDateInputInvalidOrReturnFormattedDate("2025/06/09").add(28,'day')
console.log(findOvulationDay(nextPeriodDate))
let ovulationDay = throwErrorsIfDateInputInvalidOrReturnFormattedDate("2025/06/09").add(28,'day').subtract(14,'day')
console.log(identifyFertileWindow(ovulationDay))
