def convert_to_bullet(text :str):
    lines = text.split('.')
    for i in range(len(lines)):
        if len(lines[i]) > 1:
            lines[i] = "*" + lines[i]
    text = "\n".join(lines)
    return text
     