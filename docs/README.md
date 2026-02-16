# Lebron User Guide

![Lebron Chatbot](https://camyeo.github.io/ip/Ui.png)

**Lebron** is a personal task management chatbot that helps you keep track of your todos, deadlines, and events. It's simple, fast, and works via a clean graphical interface.

---

## Quick Start

1. Ensure you have **Java 17** or above installed.
2. Download the latest `lebron.jar` from the releases page.
3. Run the application:
   ```
   java -jar lebron.jar
   ```
4. Start managing your tasks!

---

## Features

### Listing all tasks: `list`

Shows all tasks in your task list.

**Format:** `list`

**Example output:**
```
Here are the tasks in your list:
1. [T][ ] read book
2. [D][ ] submit report (by: Friday)
3. [E][X] meeting (from: 2pm to: 4pm)
```

---

### Adding a todo: `todo`

Adds a simple task without any date/time attached.

**Format:** `todo <description>`

**Example:**
```
todo read book
```

**Expected output:**
```
Got it. I've added this task:
  [T][ ] read book
Now you have 1 task in the list.
```

---

### Adding a deadline: `deadline`

Adds a task that needs to be done before a specific date/time.

**Format:** `deadline <description> by <date>`

> **Tip:** You can also use `/by` instead of `by` for the legacy format.

**Supported date formats:**
- `yyyy-MM-dd` (e.g., `2026-02-20`)
- `d/M/yyyy HHmm` (e.g., `20/2/2026 1800`)
- Any text (e.g., `Friday`, `next week`)

**Examples:**
```
deadline submit report by Friday
deadline finish project by 2026-02-20
deadline return book by 20/2/2026 1800
```

**Expected output:**
```
Got it. I've added this task:
  [D][ ] submit report (by: Friday)
Now you have 2 tasks in the list.
```

---

### Adding an event: `event`

Adds a task that occurs during a specific time period.

**Format:** `event <description> from <start> to <end>`

> **Tip:** You can also use `/from` and `/to` for the legacy format.

**Examples:**
```
event team meeting from 2pm to 4pm
event project presentation from Mon 10am to Mon 12pm
event conference from 2026-03-01 to 2026-03-03
```

**Expected output:**
```
Got it. I've added this task:
  [E][ ] team meeting (from: 2pm to: 4pm)
Now you have 3 tasks in the list.
```

---

### Marking a task as done: `mark`

Marks a task as completed.

**Format:** `mark <task number>`

**Example:**
```
mark 1
```

**Expected output:**
```
Nice! I've marked this task as done:
  [T][X] read book
```

---

### Unmarking a task: `unmark`

Marks a task as not done.

**Format:** `unmark <task number>`

**Example:**
```
unmark 1
```

**Expected output:**
```
OK, I've marked this task as not done yet:
  [T][ ] read book
```

---

### Deleting a task: `delete`

Removes a task from the list.

**Format:** `delete <task number>`

**Example:**
```
delete 2
```

**Expected output:**
```
Noted. I've removed this task:
  [D][ ] submit report (by: Friday)
Now you have 2 tasks in the list.
```

---

### Finding tasks: `find`

Searches for tasks containing a specific keyword.

**Format:** `find <keyword>`

**Example:**
```
find book
```

**Expected output:**
```
Here are the matching tasks in your list:
1. [T][ ] read book
2. [D][ ] return book (by: 20/2/2026 1800)
```

---

### Getting help: `help`

Displays a list of all available commands.

**Format:** `help`

---

### Exiting the application: `bye`

Saves your tasks and exits the application.

**Format:** `bye`

---

## Task Types Summary

| Type | Symbol | Description |
|------|--------|-------------|
| Todo | `[T]` | Simple task without date |
| Deadline | `[D]` | Task with a due date |
| Event | `[E]` | Task with start and end time |

**Status indicators:**
- `[ ]` — Not done
- `[X]` — Done

---

## Data Storage

Your tasks are automatically saved to `data/lebron.txt` and loaded when you restart the application. No manual saving required!

---

## Command Summary

| Command | Format | Example |
|---------|--------|---------|
| List | `list` | `list` |
| Todo | `todo <description>` | `todo read book` |
| Deadline | `deadline <description> by <date>` | `deadline report by Friday` |
| Event | `event <description> from <start> to <end>` | `event meeting from 2pm to 4pm` |
| Mark | `mark <task number>` | `mark 1` |
| Unmark | `unmark <task number>` | `unmark 1` |
| Delete | `delete <task number>` | `delete 2` |
| Find | `find <keyword>` | `find book` |
| Help | `help` | `help` |
| Exit | `bye` | `bye` |

---

## Acknowledgments

This project is based on the **Duke** project template created by the [SE-EDU initiative](https://se-education.org).

**Code reused/adapted:**
- JavaFX GUI setup and DialogBox pattern adapted from the [Duke Tutorial](https://se-education.org/guides/tutorials/javaFx.html)
- FXML layout structure based on SE-EDU JavaFX tutorials