def check_credit_card_type(card_number):

	card_type = ""

	if not card_number.isdigit():
		raise ValueError("Invalid characters in card number")

	split_digits = list(card_number)
	if len(split_digits) >= 13 and len(split_digits) <= 16:
		if split_digits[0] == '4':
			card_type = "Visa"
		elif split_digits[0] == '5':
			card_type = "Mastercard"
		elif split_digits[0] == '3' and split_digits[1] == '7':
			card_type = "American Express"
		elif split_digits[0] == '6':
			card_type = "Discover"
		else:
			raise ValueError("Issuer is unknown")
	else:
		raise ValueError("Invalid length")

	return "Credit Card Type: " + card_type


def sum_digits_in_card_number(card_number):
	split_digits = list(card_number)
	sum_every_second = 0

	for counter in range(1, 3):
		for index in range(len(split_digits) - counter, -1, -2):
			to_digit = int(split_digits[index]) * 2
			digit_to_string = str(to_digit)
			if len(digit_to_string) == 2:
				to_digit = int(digit_to_string[0]) + int(digit_to_string[1])
			sum_every_second += to_digit

	return sum_every_second


def check_card_validity(summed_digits):
	if summed_digits % 10 == 0:
		return "Credit Card Validity Status: Valid"
	else:
		return "Credit Card Validity Status: Invalid"


card_number = "3799831619690403"
print(check_credit_card_type(card_number))
print("Credit Card Number: " + card_number)
print("Credit Card Digit Length: " + str(len(card_number)))
print(check_card_validity(sum_digits_in_card_number(card_number)))