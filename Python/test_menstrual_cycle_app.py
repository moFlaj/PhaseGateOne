import unittest
from datetime import datetime, date, timedelta
from menstrual_cycle_app import *


class test_menstrual_cycle_app(unittest.TestCase):
	

	def setUp(self):
		self.date_input = '2025/06/08'

	def test_raise_errors_if_date_format_invalid_raises_error_if_entered_date_format_is_invalid(self):
		date_input = '2025-06-08'
		self.assertRaises(ValueError, raise_errors_if_format_invalid_or_return_formatted_date, date_input)

	def test_return_day_of_week_only_accepts_date_objects_as_arguments(self):
		self.assertRaises(ValueError, return_day_of_week, self.date_input)
		
	def test_return_day_of_week_returns_correct_day_of_date_input(self):

		format_date_input = raise_errors_if_format_invalid_or_return_formatted_date(self.date_input)
		actual = return_day_of_week(format_date_input)
		expected = 'Sunday'
		self.assertEqual(actual, expected)

	def test_period_flow_days_returns_estimation_of_period_flow_days(self):
		user_average_no_of_flow_days = 4
		actual = period_flow_days(self.date_input, user_average_no_of_flow_days)
		expected = 'Your period flow will last from Sunday, 08 June, 2025 to Thursday, 12 June, 2025.'
		self.assertEqual(actual, expected)
	
	def test_period_flow_days_throws_error_if_second_argument_is_not_of_type_int(self):
		user_average_no_of_flow_days = 4.5
		self.assertRaises(ValueError, period_flow_days, self.date_input, user_average_no_of_flow_days)

	def test_next_period_start_date(self):
		user_average_cycle_length = 21
		actual = next_period_start_date(self.date_input, user_average_cycle_length)
		expected = 'Your next period begins on Sunday, 29 June, 2025.'
		self.assertEqual(actual, expected)

	def test_next_period_start_date_raises_error_if_date_format_is_invalid(self):
		date_input = '2025-06-08'
#Format: YYYY/MM/DD
		user_average_cycle_length = 21
		self.assertRaises(ValueError, next_period_start_date, date_input, user_average_cycle_length)

	def test_next_period_start_date_raises_error_if_cycle_length_is_not_an_integer(self):
		user_average_cycle_length = '21'
		self.assertRaises(ValueError, next_period_start_date, self.date_input, user_average_cycle_length)
