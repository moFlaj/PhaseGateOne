
from datetime import datetime, date, timedelta

def raise_errors_if_format_invalid_or_return_formatted_date(date_input):

	try:
		checked_date = datetime.strptime(date_input, "%Y/%m/%d").date()

	except:
		raise ValueError("Invalid date format or values. Expected format: YYYY/MM/DD.");

	return checked_date


def return_day_of_week(formatted_date):
	if not isinstance(formatted_date, date):
		raise ValueError("Invalid input. Field is only for dates.")
 
	return formatted_date.strftime("%A")
	

def period_flow_days(date_input, number_of_flow_days):
	formatted_date_input = raise_errors_if_format_invalid_or_return_formatted_date(date_input)

	if not isinstance(number_of_flow_days, int):
		raise ValueError("Flow days must be whole number.")

	date_of_end_of_flow = formatted_date_input + timedelta(number_of_flow_days)
	
	return 'Your period flow will last from ' + return_day_of_week(formatted_date_input) + ', ' + formatted_date_input.strftime("%d %B, %Y") + ' to ' + return_day_of_week(date_of_end_of_flow) + ", " + date_of_end_of_flow.strftime("%d %B, %Y") + '.'

def next_period_start_date(date_of_first_flow, cycle_length):
	formatted_date_input = raise_errors_if_format_invalid_or_return_formatted_date(date_of_first_flow)

	if not isinstance(cycle_length, int):
		raise ValueError("Flow days must be whole number.")

	return 'Your next period begins on ' + return_day_of_week(formatted_date_input + timedelta(cycle_length)) + ", " + (formatted_date_input + timedelta(cycle_length)).strftime("%d %B, %Y") + '.'

def find_ovulation_day(next_period_start_date):

# Ovulation day usually occurs about 14 days before the next period starts.
	ovulation_day = 14

	if not isinstance(next_period_start_date, date):
		raise ValueError("Invalid input. Field is only for dates.")

	return "Ovulation day of current period is on " + return_day_of_week(next_period_start_date - timedelta(ovulation_day)) + ', ' + (next_period_start_date - timedelta(ovulation_day)).strftime("%d %B, %Y") + '.'
	
