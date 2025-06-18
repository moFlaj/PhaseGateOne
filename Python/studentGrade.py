def input_student_scores(all_students, no_of_students, no_of_subjects):
	all_students = []
	student_counter = 1

	for _ in range(no_of_students):
		each_student = []
		total = 0
		print(f"Entering score for student {student_counter}")
		for subject_score in range(no_of_subjects):
			while True:
				score = float(input(f"Enter score for subject {subject_score + 1}: "))
				if 0 <= score <= 100:
					total += score
					each_student.insert(subject_score, score)
					break
				else:
					print("Invalid score.\nScore must be between 0 and 100!")
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



no_of_students = int(input('How many students do you have? '))
no_of_subjects = int(input('How many subjects do they offer? '))
all_students = []
all_students = input_student_scores(all_students, no_of_students, no_of_subjects)
all_students = position_of_students(all_students)
display_record_sheet(all_students, no_of_subjects)
subject_summary(all_students, no_of_subjects, no_of_students)

