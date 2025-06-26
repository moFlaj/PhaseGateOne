def input_student_scores(all_students, no_of_students, no_of_subjects):
	all_students = []
	student_counter = 1

	for _ in range(no_of_students):
		each_student = []
		total = 0
		print(f"Entering score for student {student_counter}")
		for subject_score in range(no_of_subjects):
			while True:
				try:
					score = float(input(f"Enter score for subject {subject_score + 1}: "))
					if 0 <= score <= 100:
						total += score
						each_student.insert(subject_score, score)
						break
					else:
						print("Invalid score.\nScore must be between 0 and 100!")
				except:
					print("You may have entered an invalid character. Retry")

		each_student.append(total)
		each_student.append(total / no_of_subjects)
		each_student.append(0)  # position
		all_students.append(each_student)
		print("Saving >>>>>>>>>>>>>>>>\nSaved successfully")
		student_counter += 1

	return all_students


def position_of_students(all_students):
	average_scores_of_each_student = []

	for each_student in all_students:
		average_scores_of_each_student.append(each_student[-2])

	for eachIndex in range(len(average_scores_of_each_student) - 1):
		for spanIndex in range(eachIndex + 1, len(average_scores_of_each_student)):
			if average_scores_of_each_student[eachIndex] < average_scores_of_each_student[spanIndex]:
				average_scores_of_each_student[eachIndex], average_scores_of_each_student[spanIndex] = (
				average_scores_of_each_student[spanIndex],
				average_scores_of_each_student[eachIndex],)

	student_has_position = []

	for each_student in all_students:
		for highest_to_lowest in range(len(average_scores_of_each_student)):
			if each_student[-2] == average_scores_of_each_student[highest_to_lowest] and each_student not in student_has_position:
				each_student[-1] = highest_to_lowest + 1
				student_has_position.append(each_student)

	return all_students


def display_record_sheet(all_students, no_of_subjects):
	print("===============================================")
	header = ["STUDENT"]
	for index in range(1, no_of_subjects + 1):
		header.append(f"SUB{index}")
	header += ["TOTAL", "AVE", "POS"]

	for each_header in header:
		print(each_header, end="\t")
		if each_header == "STUDENT":
			print("\t", end="")

	print("\n" + "=" * 90)

	student_counter = 1
	for each_student_and_scores in all_students:
		print(f"Student {student_counter}", end="\t")
		for index, value in enumerate(each_student_and_scores):
			if index == len(each_student_and_scores) - 1:
				print(int(value), end="")
			elif index == len(each_student_and_scores) - 2:
				print(f"{value:.2f}", end="\t")
			else:
				print(value, end="\t")
		print()
		student_counter += 1

	print("=" * 90)
	print("=" * 90)


def subject_summary(all_students, no_of_subjects, no_of_students):

	for each_subject_index in range(no_of_subjects):
		
		highest = float('-inf')
		lowest = float('inf')
		highest_scoring_student = 0
		lowest_scoring_student = 0
		subject_total = 0
		pass_count = 0
		fail_count = 0
		for index, each_student_score_in_subject in enumerate(all_students):
			score = each_student_score_in_subject[each_subject_index]
			if score > highest:
				highest = score
				highest_scoring_student = index + 1
			if score < lowest:
				lowest = score
				lowest_scoring_student = index + 1
			if score >= 50:
				pass_count += 1
			else:
  				fail_count += 1
			subject_total += score

		print(f"Subject {each_subject_index + 1}")
		print(f"Highest scoring student is: Student {highest_scoring_student} scoring {highest:.2f}.")
		print(f"Lowest scoring student is: Student {lowest_scoring_student} scoring {lowest:.2f}.")
		print(f"Total score is: {subject_total:.2f}.")
		print(f"Average score is: {subject_total / no_of_students:.2f}.")
		print(f"Number of passes: {pass_count}.")
		print(f"Number of failures: {fail_count}.")


def class_summary(all_students, no_of_subjects, no_of_students):
	compare_subject_failures = 0
	compare_subject_passes = 0
	overall_highest_subject_score = float('-inf')
	overall_lowest_subject_score = float('inf')
	all_students_index = 0
	highest_score_in_all_index = 0
	subject_with_highest_score = 0
	lowest_score_in_all = 0
	subject_with_lowest_score = 0
	sum_student_total = 0
	highest_students_total = float('-inf')
	lowest_students_total = float('inf')
	best_graduating_student = 0
	worst_graduating_student = 0
	hardest_subject_index = 0
	easiest_subject_index = 0
	class_total_score = 0
	count_no_of_passes = 0
	count_no_of_failures = 0

	for each_subject_index in range(no_of_subjects):
		for each_student_score_in_subject in all_students:
			if each_student_score_in_subject[each_subject_index] > overall_highest_subject_score:
				overall_highest_subject_score = each_student_score_in_subject[each_subject_index]
				highest_score_in_all_index = all_students_index + 1
				subject_with_highest_score = each_subject_index + 1

			if each_student_score_in_subject[each_subject_index] < overall_lowest_subject_score:
				overall_lowest_subject_score = each_student_score_in_subject[each_subject_index]
				lowest_score_in_all = all_students_index + 1
				subject_with_lowest_score = each_subject_index + 1

			if each_student_score_in_subject[each_subject_index] >= 50:
				count_no_of_passes += 1
			else:
				count_no_of_failures += 1

			if each_subject_index == 0:
				for add_scores_of_student in range(no_of_subjects):
					sum_student_total += each_student_score_in_subject[add_scores_of_student]
				class_total_score += sum_student_total

				if sum_student_total > highest_students_total:
					highest_students_total = sum_student_total
					best_graduating_student = all_students_index + 1
				if sum_student_total < lowest_students_total:
					lowest_students_total = sum_student_total
					worst_graduating_student = all_students_index + 1

			all_students_index += 1
			sum_student_total = 0

		if each_subject_index == 0:
			compare_subject_failures = count_no_of_failures
			compare_subject_passes = compare_subject_passes
			hardest_subject_index = each_subject_index + 1
			easiest_subject_index = each_subject_index + 1
		else:
			if count_no_of_passes > compare_subject_passes:
				compare_subject_passes = count_no_of_passes
				easiest_subject_index = each_subject_index + 1
			elif count_no_of_failures > compare_subject_failures:
				compare_subject_failures = count_no_of_failures
				hardest_subject_index = each_subject_index + 1

		count_no_of_failures = 0
		count_no_of_passes = 0
		all_students_index = 0

	if compare_subject_passes > 0 and compare_subject_failures > 0:
		if compare_subject_failures != compare_subject_passes:
			print(f"\n\nThe hardest subject is subject {hardest_subject_index} with {compare_subject_failures} failures.")
			print(f"The easiest subject is subject {easiest_subject_index} with {compare_subject_passes} passes.")
			print(f"The overall highest score is scored by student {highest_score_in_all_index} in subject {subject_with_highest_score} scoring {overall_highest_subject_score:.2f}.")
			print(f"The overall lowest score is scored by student {lowest_score_in_all} in subject {subject_with_lowest_score} scoring {overall_lowest_subject_score:.2f}.")
		else:
			print("There were equal number of passes and failures in all subjects.")
			print(f"The overall highest score is scored by student {highest_score_in_all_index} in subject {subject_with_highest_score} scoring {overall_highest_subject_score:.2f}.")
			print(f"The overall lowest score is scored by student {lowest_score_in_all} in subject {subject_with_lowest_score} scoring {overall_lowest_subject_score:.2f}.")

		print(f"\n\n\nCLASS SUMMARY\n\n")
		print(f"Best graduating student is: student {best_graduating_student} scoring {highest_students_total:.2f}.")
		print(f"Worst graduating student is: student {worst_graduating_student} scoring {lowest_students_total:.2f}.\n\n\n")
	elif compare_subject_passes > 0 and compare_subject_failures == 0:
		print("No failures")
	elif compare_subject_passes == 0 and compare_subject_failures > 0:
		print("All students failed all subjects")

	print(f"Class total score is {class_total_score:.2f}.")
	print(f"Class average score is {class_total_score / no_of_students:.2f}.")



run_app = True

while run_app:
	try:
		no_of_students = input("How many students do you have? ")
		if not no_of_students.isdigit():
			raise ValueError("You may have entered an invalid character.")
		convert_stu = int(no_of_students)
		if convert_stu < 1:
			raise ValueError("This field requires a positive integer.")

		no_of_subjects = input("How many subjects do they offer? ")
		if not no_of_subjects.isdigit():
			raise ValueError("You may have entered an invalid character.")
		convert_sub = int(no_of_subjects)
		if convert_sub < 1:
			raise ValueError("This field requires a positive integer. Retry.")

		all_students = []
		all_students = input_student_scores(all_students, convert_stu, convert_sub)
		all_students = position_of_students(all_students)
		display_record_sheet(all_students, convert_sub)
		subject_summary(all_students, convert_sub, convert_stu)
		class_summary(all_students, convert_sub, convert_stu)
		run_app = False

	except ValueError as invalid_input:
		print(invalid_input)
