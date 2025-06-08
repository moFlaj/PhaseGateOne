import unittest
from datetime import datetime, date, timedelta
from menstrual_cycle_app import *


class test_menstrual_cycle_app(unittest.TestCase):
	

	def setUp(self):
		self.date_input = '2025/06/08'


	def test_raise_errors_if_date_format_invalid_raises_error_if_entered_date_format_is_invalid(self):
		date_input = '2025-06-08'
		self.assertRaises(ValueError, raise_errors_if_format_invalid, date_input)

	def test_return_day_of_week_only_accepts_date_objects_as_arguments(self):
		self.assertRaises(ValueError, return_day_of_week, self.date_input)

	def test_return_day_of_week_returns_correct_day_of_date_input(self):

		format_date_input = raise_errors_if_format_invalid(self.date_input)
		actual = return_day_of_week(format_date_input)
		expected = 'Sunday'
		self.assertEqual(actual, expected)
