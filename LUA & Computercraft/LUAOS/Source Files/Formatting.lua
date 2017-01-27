--API for different basic drawing actions--
local function termWrite(x, y, text, color)
	term.setTextColor(colors.white)
    if color == "red" then term.setBackgroundColor((colors.red))
	elseif color == "lime" then term.setBackgroundColor((colors.lime)) 
	elseif color == "green" then term.setBackgroundColor((colors.green))
	elseif color == "white" then term.setBackgroundColor((colors.white)) term.setTextColor(colors.black)
	elseif color == "black" then term.setBackgroundColor((colors.black))
	end
	term.setCursorPos(x,y)
    term.write(text)
end
local function termClear()
	term.setCursorPos(1,1)
	term.setBackgroundColor(colors.black)
	term.setTextColor(colors.white)
	term.clear()
end 
local function rectangle(x, y, length, width, color)
	term.setTextColor(colors.white)
    if color == "red" then term.setBackgroundColor((colors.red))
	elseif color == "lime" then term.setBackgroundColor((colors.lime)) 
	elseif color == "green" then term.setBackgroundColor((colors.green))
	elseif color == "white" then term.setBackgroundColor((colors.white)) term.setTextColor(colors.black)
	elseif color == "black" then term.setBackgroundColor((colors.black))
	end
	
	for i = 0, lenght do
		for j = 0, width do
			term.setCursorPos(x + i, y + j)
			term.write(" ")
		end
	end
end
