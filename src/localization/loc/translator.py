#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Wed May 29 16:20:04 2024

@author: François
"""

from googletrans import Translator

TARGET = input("Code Langage ISO 639-1 : ")

def translate_text(text: str, target_lang: str):
    """
    Translates the given text to the specified target language.

    Parameters:
    - text (str): The text to be translated.
    - target_lang (str): The ISO 639-1 code of the target language.

    Returns:
    - str: The translated text.
    """
    # Initialize the Translator
    translator = Translator()

    # Perform the translation
    result = translator.translate(text, dest=target_lang)

    # Return the translated text
    return result.text

text = {}

def decode_line(line):
    line = line.replace("\n", "")
    if line!="":
        if not line.startswith("#"):
            parts = line.split(" ", 1)
            print(parts)
            text[parts[0]] = translate_text(parts[1], TARGET)



with open("en.loc", "r") as f:
    for line in f:
        decode_line(line)
        
        
with open(TARGET+".loc", "w") as wf:
    for key, value in text.items():
        wf.write(key+" "+value + '\n')