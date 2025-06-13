
import re
from decimal import Decimal, ROUND_HALF_UP



def set_customer_name(first_name, last_name, middle_name, customerInfo):
	
	if first_name.isspace() or last_name.isspace() or middle_name.isspace():
		raise ValueError("This is a required field. Cannot be left blank.")
#Still raises AttributeError if any of the names is None, as 'NoneType' object has no attribute 'isspace'

	if not re.fullmatch(r"[a-zA-Z]+(-[a-zA-Z]+)*", first_name) or not re.fullmatch(r"[a-zA-Z]+(-[a-zA-Z]+)*", last_name) or not re.fullmatch(r"[a-zA-Z]+(-[a-zA-Z]+)*", middle_name):
		raise ValueError("Name field may contain invalid characters or more than one input was enetered in field.")
#Only letters and hyphens are allowed to be in names. Hyphens are not allowed to start a name or appear consecutively.

	customerInfo[0] = "Customer name: " + first_name.capitalize() + " " + last_name.capitalize() + " " + middle_name.capitalize() 




def get_customer_name(customerInfo):
	
	return customerInfo[0]


def return_product_if_detected(product_input, all_products):

	itemsMatched = []
	selectedItem = ""
	counter = 0

	for index in range(len(all_products)):
		if (product_input.lower() == all_products[index].lower() or product_input.lower() in all_products[index].lower()):
			counter+=1
			itemsMatched.append(str(counter) + '. ' + all_products[index])

	for eachItemsMatched in itemsMatched:
		print(eachItemsMatched)

	while True:
		select = int(input("Select option: "))
		if select >= 1 and select <= len(itemsMatched):
			selectedItem = itemsMatched[select-1]
			selectedItem = selectedItem[selectedItem.index(" ") + 1:]
			break
		else:
			print("Enter valid option number")

	return selectedItem

def get_product_price(product, all_products, all_prices, purchased_item_info):

	for index in range(len(all_products)):
		if (product.lower() == all_products[index].lower() or product.lower() in all_products[index].lower()):
			purchased_item_info[0] = product
			purchased_item_info[2] = all_prices[index]

	return "NGN " + purchased_item_info[2]


def add_purchased_product_to_list(purchased_item_info, pieces, customer_purchase):
	try:
		quantity = int(pieces)
		if quantity < 1:
			raise ValueError("Quantity entered is not valid.")

		purchased_item_info[1] = pieces
		total_price = Decimal(purchased_item_info[2]) * Decimal(purchased_item_info[1])
		purchased_item_info[3] = str(total_price)
		customer_purchase.append(purchased_item_info)
		return "Product added successfully. Add more items?\nEnter Yes/No"

	except ValueError as e:
		raise ValueError("Quantity entered is not a valid integer.") from e



def compute_total_cost_of_items_purchased(customer_purchase):
	total = Decimal("0")
	for item in customer_purchase:
		if item == customer_purchase[0]:
			continue
		total += Decimal(item[3])
    
	return total

def value_added_tax(sum):
	added_tax = (Decimal("7.5") / Decimal("100")) * sum
	return added_tax.quantize(Decimal("0.01"), rounding=ROUND_HALF_UP)

def in_voice_display(store_info, customer_purchase, sum_total_cost):

	receipt_design = [
        "===============================================================",
        "--------------------------------------------------------------"
	]
	change = 0
	print()
    
	for info in store_info:
		print(info)
    
	for display_invoice in customer_purchase:
		print(receipt_design[change])
		for item_info in display_invoice:
			print("\t" + item_info, end='')
			if display_invoice[0] == item_info:
				print("\t", end='')
		print()
		change = 1

	print(receipt_design[1])
	print("\t\tSum Total: " + str(sum_total_cost))
	vat = value_added_tax(sum_total_cost)
	print("\t\tVAT @ 7.5%: " + str(vat))
	print(receipt_design[0])
	bill_total = sum_total_cost + vat
	print("\t\tBill Total: " + str(bill_total))
	print(receipt_design[0])
	print("THIS IS NOT A RECEIPT, KINDLY PAY " + str(bill_total))
	print(receipt_design[0])




def receipt_display(sum, customer_pay, store_info, customer_purchase):
	try:
		convert_customer_pay = Decimal(customer_pay)
		vat = value_added_tax(sum)
		bill_total = sum + vat
		less_than_required = convert_customer_pay.compare(bill_total)
		negative_amount_input = convert_customer_pay.compare(Decimal('0'))

		if less_than_required < 0 or negative_amount_input < 0:
			raise ValueError("Invalid input. Enter right amount.")

		receipt_design = [
			"===============================================================",
			"--------------------------------------------------------------"
		]
		change = 0

		print()

		for index in range(len(store_info)):
			print(store_info[index])

		for display_invoice in customer_purchase:
			print(receipt_design[change])
			for item_info in display_invoice:
				print("\t" + item_info, end='')
				if display_invoice[0] == item_info:
					print("\t", end='')
			print()
			change = 1

		

		print(receipt_design[1])
		print("\t\tSum Total: " + str(sum))
		print("\t\tVAT @ 7.5%: " + str(vat))
		print(receipt_design[0])
		print("\t\tBill Total: " + str(bill_total))
		print("\t\tAmount paid: " + str(convert_customer_pay))

		balance = convert_customer_pay - bill_total
		print("\t\tBalance: " + str(balance))
		print(receipt_design[0])
		print("\tTHANK YOU FOR YOUR PATRONAGE.")
		print(receipt_design[0])

	except ValueError as invalid_input:
		print(invalid_input)



from datetime import datetime

customer_purchase = [["ITEM", "QTY", "PRICE", "TOTAL(NGN)"]]
customer_info = [""]
store_info = [
	"SEMICOLON STORES",
	"MAIN BRANCH",
	"LOCATION: 312 HERBERT MACAULAY WAY, SABO YABA, LAGOS.",
	"TEL: 0823452776",
	None,
	None,
	None
]
all_products = [
	"ChiActive 10g", "Cornflakes 15g", "Cornflakes 35g", "Frozen chicken 15g",
	"Frozen Chicken 20g", "Milk 25g", "Milk 50g", "Milo 20g", "Ribena 8g",
	"Rice 50g", "Semolina 30g"
]
all_prices = [
	"1700.00", "1200.00", "3000.00", "8000.00", "15700.00", "2200.00",
	"4200.00", "3500.00", "1100.00", "5450.00", "5800.00"
]

"""check_out = True

while check_out:
	print("What is the customer's full name?")
	first_name = input()
	last_name = input()
	middle_name = input()
	set_customer_name(first_name, last_name, middle_name, customer_info)
	store_info[6] = get_customer_name(customer_info)

	check_item_bought = True
	while check_item_bought:
		purchased_item_info = [None] * 4
		print("What did the customer buy?")
		product_input = input()
		product_input = return_product_if_detected(product_input, all_products)
		print("How much per unit?")
		print(get_product_price(product_input, all_products, all_prices, purchased_item_info))
		print("How many pieces?")
		quantity = input()
		continue_adding_items = add_purchased_product_to_list(purchased_item_info, quantity, customer_purchase)

		continue_adding = True
		while continue_adding:
			print(continue_adding_items)
			yes_or_no = input()
			if yes_or_no.lower() == "yes":
				continue_adding = False
			elif yes_or_no.lower() == "no":
				continue_adding = False
				check_item_bought = False
				check_out = False
			else:
				print("Enter valid response!")

	sum_total_cost_of_items = compute_total_cost_of_items_purchased(customer_purchase)
	print("Full name of cashier?")
	first_name = input()
	last_name = input()
	middle_name = input()
	store_info[5] = "Cashier: " + first_name.capitalize() + " " + last_name.capitalize() + " " + middle_name.capitalize()

	date_and_current_time = datetime.now()
	store_info[4] = "Date: " + date_and_current_time.strftime("%d-%b-%Y %H:%M:%S")

	in_voice_display(store_info, customer_purchase, sum_total_cost_of_items)
	print("How much did customer pay?")
	customer_pay = input()
	receipt_display(sum_total_cost_of_items, customer_pay, store_info, customer_purchase)
"""