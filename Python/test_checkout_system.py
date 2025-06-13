import unittest
from checkoutSystem import set_customer_name, get_customer_name, get_product_price, add_purchased_product_to_list, compute_total_cost_of_items_purchased


class test_checkout_system(unittest.TestCase):

	def setUp(self):
		self.first_name = "adebola"
		self.last_name = "folorunsho"
		self.middle_name = "ayomide"
		self.customer_info = [None] * 1
		self.all_products = ["ChiActive 10g", "Cornflakes 15g", "Cornflakes 35g", "Frozen chicken 15g",
			"Frozen Chicken 20g", "Milk 25g", "Milk 50g", "Milo 20g", "Ribena 8g",
			"Rice 50g", "Semolina 30g"]

		self.all_prices = ["1700.00", "1200.00", "3000.00", "8000.00", "15700.00", "2200.00",
			"4200.00", "3500.00", "1100.00", "5450.00", "5800.00"]

		self.purchased_item_info = [None] * 4
		self.customer_purchase = []
		self.customer_purchase.append(["ITEM", "QTY", "PRICE", "TOTAL(NGN)"])

	def test_set_customer_name_raises_attribute_error_if_name_is_none(self):
		self.first_name = None
		with self.assertRaises(AttributeError):
			set_customer_name(self.first_name, self.last_name, self.middle_name, self.customer_info)

	def test_set_customer_name_raises_ValueError_if_field_is_left_blank_or_empty(self):
		self.last_name = " "
		with self.assertRaises(ValueError):
			set_customer_name(self.first_name, self.last_name, self.middle_name, self.customer_info)

	def test_set_customer_name_raises_exception_if_invalid_characters_or_digits_are_entered(self):
		self.middle_name = "Ad$bola"
		with self.assertRaises(ValueError):
			set_customer_name(self.first_name, self.last_name, self.middle_name, self.customer_info)

	def test_set_customer_name_raises_error_if_hyphens_are_used_to_start_a_name(self):
		self.first_name = "-debola"
		with self.assertRaises(ValueError):
			set_customer_name(self.first_name, self.last_name, self.middle_name, self.customer_info)

	def test_set_customer_name_does_not_throw_error_if_name_is_a_compound_name(self):
		self.first_name = "Ade-bola"
		try:
			set_customer_name(self.first_name, self.last_name, self.middle_name, self.customer_info)
		except Exception as e:
			self.fail(f"Unexpected exception raised: {e}")

	def test_get_customer_name_returns_first_letters_of_names_capitalized_if_names_are_valid(self):

		set_customer_name(self.first_name, self.last_name, self.middle_name, self.customer_info)
		self.assertEqual("Customer name: Adebola Folorunsho Ayomide", get_customer_name(self.customer_info))

	def test_get_product_price_returns_price_of_product_entered_if_size_is_included(self):
		product = "milk 25g"
		self.assertEqual("NGN 2200.00", get_product_price(product, self.all_products, self.all_prices, self.purchased_item_info))

	def test_add_purchased_product_to_list_returns_successful_if_quantity_entered_is_a_valid_integer(self):
		product = "milk 25g"
		get_product_price(product, self.all_products, self.all_prices, self.purchased_item_info)
		quantity = "2"
		actual = add_purchased_product_to_list(self.purchased_item_info, quantity, self.customer_purchase)
		expected = "Product added successfully. Add more items?\nEnter Yes/No"
		self.assertEqual(expected, actual)

	def test_add_purchased_product_to_list_throws_error_if_quantity_entered_is_not_a_valid_integer(self):
		product = "milk 25g"
		get_product_price(product, self.all_products, self.all_prices, self.purchased_item_info)
		quantity = "2.5"
		with self.assertRaises(ValueError):
			add_purchased_product_to_list(self.purchased_item_info, quantity, self.customer_purchase)

	def test_add_purchased_product_to_list_throws_error_if_quantity_entered_is_less_than_one(self):
		product = "milk 25g"
		get_product_price(product, self.all_products, self.all_prices, self.purchased_item_info)
		quantity = "0"
		with self.assertRaises(ValueError):
			add_purchased_product_to_list(self.purchased_item_info, quantity, self.customer_purchase)

	def test_compute_total_cost_of_items_purchased(self):
		product = "milk 25g"
		get_product_price(product, self.all_products, self.all_prices, self.purchased_item_info)
		add_purchased_product_to_list(self.purchased_item_info, "2", self.customer_purchase)

		self.purchased_item_info = [None] * 4
		product = "cornflakes 35g"
		get_product_price(product, self.all_products, self.all_prices, self.purchased_item_info)
		add_purchased_product_to_list(self.purchased_item_info, "5", self.customer_purchase)

		actual = str(compute_total_cost_of_items_purchased(self.customer_purchase))
		expected = "19400.00"
		self.assertEqual(expected, actual)

	
"""	
def test_return_product_if_detected_returns_list_of_items_matching_search_input_for_cashier_to_pick(self):
		self.search_input = "mil"
		actual = return_product_if_detected(self.search_input, self.all_products)
		expected = "Milk 25g"
		self.assertEqual(expected, actual)

Interactive input is needed in this function. This is why I commented it out.
"""